package org.example.businesslogic.discount.service;

import lombok.RequiredArgsConstructor;
import org.example.businesslogic.common.exception.BusinessException;
import org.example.businesslogic.common.exception.NotFoundException;
import org.example.businesslogic.discount.dto.ItemPriceResponse;
import org.example.businesslogic.discount.dto.ItemRequest;
import org.example.businesslogic.discount.dto.PriceRequest;
import org.example.businesslogic.discount.dto.PriceResponse;
import org.example.businesslogic.discount.model.Category;
import org.example.businesslogic.discount.model.Customer;
import org.example.businesslogic.discount.model.DiscountCode;
import org.example.businesslogic.discount.model.Product;
import org.example.businesslogic.discount.repository.CategoryRepository;
import org.example.businesslogic.discount.repository.DiscountCodeRepository;
import org.example.businesslogic.discount.repository.DiscountCustomerRepository;
import org.example.businesslogic.discount.repository.DiscountProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.example.businesslogic.common.util.MoneyUtils.round;

@Service
@RequiredArgsConstructor
public class PriceService {

    private static final double MAX_DISCOUNT_PERCENT = 30;
    private static final double VIP_EXTRA_DISCOUNT = 5;
    private static final double FREE_SHIPPING_LIMIT = 2000;
    private static final double SHIPPING_FEE = 50;

    private final DiscountProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final DiscountCustomerRepository customerRepository;
    private final DiscountCodeRepository discountCodeRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public List<DiscountCode> getDiscountCodes() {
        return discountCodeRepository.findAll();
    }

    public PriceResponse calculate(PriceRequest request) {
        Customer customer = customerRepository.findById(request.customerId())
                .orElseThrow(() -> new NotFoundException("Customer not found"));

        List<DiscountCode> codes = loadDiscountCodes(request.discountCodes());
        checkCodesCanBeCombined(codes);
        double codesPercent = codes.stream().mapToDouble(DiscountCode::getPercent).sum();

        List<ItemPriceResponse> items = new ArrayList<>();
        double subtotal = 0;
        double totalDiscount = 0;

        for (ItemRequest itemRequest : request.items()) {
            Product product = productRepository.findById(itemRequest.productId())
                    .orElseThrow(() -> new NotFoundException("Product " + itemRequest.productId() + " not found"));

            double discountPercent = getDiscountPercent(product, customer, codesPercent);
            double linePrice = product.getPrice() * itemRequest.quantity();
            double lineDiscount = linePrice * discountPercent / 100;

            subtotal += linePrice;
            totalDiscount += lineDiscount;
            items.add(new ItemPriceResponse(product.getName(), itemRequest.quantity(), product.getPrice(),
                    discountPercent, round(linePrice - lineDiscount)));
        }

        double priceAfterDiscount = subtotal - totalDiscount;
        boolean freeShipping = priceAfterDiscount >= FREE_SHIPPING_LIMIT;
        double shippingFee = freeShipping ? 0 : SHIPPING_FEE;

        return new PriceResponse(items, round(subtotal), round(totalDiscount), shippingFee,
                freeShipping, round(priceAfterDiscount + shippingFee));
    }

    // category discount + VIP discount + codes, but never more than 30%
    private double getDiscountPercent(Product product, Customer customer, double codesPercent) {
        if (!product.isDiscountEligible()) {
            return 0;
        }

        Category category = categoryRepository.findById(product.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found"));

        double percent = category.getDiscountPercent() + codesPercent;
        if (customer.isVip()) {
            percent += VIP_EXTRA_DISCOUNT;
        }
        return Math.min(percent, MAX_DISCOUNT_PERCENT);
    }

    private List<DiscountCode> loadDiscountCodes(List<String> codes) {
        if (codes == null || codes.isEmpty()) {
            return List.of();
        }

        // remove duplicates so the same code is not counted twice
        Set<String> uniqueCodes = new LinkedHashSet<>();
        for (String code : codes) {
            uniqueCodes.add(code.trim().toUpperCase());
        }

        List<DiscountCode> result = new ArrayList<>();
        for (String code : uniqueCodes) {
            DiscountCode discountCode = discountCodeRepository.findByCode(code)
                    .orElseThrow(() -> new BusinessException("Discount code " + code + " is not valid"));
            result.add(discountCode);
        }
        return result;
    }

    private void checkCodesCanBeCombined(List<DiscountCode> codes) {
        for (int i = 0; i < codes.size(); i++) {
            for (int j = i + 1; j < codes.size(); j++) {
                DiscountCode first = codes.get(i);
                DiscountCode second = codes.get(j);
                if (!first.canBeUsedWith(second)) {
                    throw new BusinessException("Discount codes " + first.getCode() + " and "
                            + second.getCode() + " cannot be used together");
                }
            }
        }
    }
}
