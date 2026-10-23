package org.example.businesslogic.discount.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.businesslogic.discount.dto.PriceRequest;
import org.example.businesslogic.discount.dto.PriceResponse;
import org.example.businesslogic.discount.model.DiscountCode;
import org.example.businesslogic.discount.model.Product;
import org.example.businesslogic.discount.service.PriceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/prices")
@RequiredArgsConstructor
public class PriceController {

    private final PriceService priceService;

    @GetMapping("/products")
    public List<Product> getProducts() {
        return priceService.getProducts();
    }

    @GetMapping("/codes")
    public List<DiscountCode> getCodes() {
        return priceService.getDiscountCodes();
    }

    @PostMapping("/calculate")
    public PriceResponse calculate(@Valid @RequestBody PriceRequest request) {
        return priceService.calculate(request);
    }
}
