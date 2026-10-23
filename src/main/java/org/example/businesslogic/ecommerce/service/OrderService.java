package org.example.businesslogic.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.example.businesslogic.common.exception.BusinessException;
import org.example.businesslogic.common.exception.NotFoundException;
import org.example.businesslogic.ecommerce.dto.CreateOrderRequest;
import org.example.businesslogic.ecommerce.dto.OrderItemRequest;
import org.example.businesslogic.ecommerce.dto.UpdateOrderRequest;
import org.example.businesslogic.ecommerce.model.Order;
import org.example.businesslogic.ecommerce.model.OrderItem;
import org.example.businesslogic.ecommerce.model.OrderStatus;
import org.example.businesslogic.ecommerce.model.StoreProduct;
import org.example.businesslogic.ecommerce.repository.OrderRepository;
import org.example.businesslogic.ecommerce.repository.StoreCustomerRepository;
import org.example.businesslogic.ecommerce.repository.StoreProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.example.businesslogic.common.util.MoneyUtils.round;

@Service
@RequiredArgsConstructor
public class OrderService {

    private static final double FREE_SHIPPING_LIMIT = 5000;
    private static final double SHIPPING_FEE = 60;

    private final OrderRepository orderRepository;
    private final StoreProductRepository productRepository;
    private final StoreCustomerRepository customerRepository;

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public List<StoreProduct> getProducts() {
        return productRepository.findAll();
    }

    public synchronized Order create(CreateOrderRequest request) {
        customerRepository.findById(request.customerId())
                .orElseThrow(() -> new NotFoundException("Customer not found"));

        if (orderRepository.existsUnpaidByCustomerId(request.customerId())) {
            throw new BusinessException("Customer has an unpaid order, please pay it first");
        }

        // build and check all items before touching the stock
        List<OrderItem> items = buildItems(request.items());

        Order order = new Order();
        order.setCustomerId(request.customerId());
        order.setStatus(OrderStatus.PENDING);
        order.setPaid(false);
        order.setCreatedAt(LocalDateTime.now());
        setItemsAndTotals(order, items);
        orderRepository.save(order);

        // order is saved, now we can reduce the stock
        decreaseStock(items);
        return order;
    }

    public synchronized Order update(Long id, UpdateOrderRequest request) {
        Order order = findOrder(id);

        if (order.getStatus() == OrderStatus.CONFIRMED) {
            throw new BusinessException("Order cannot be modified after it is CONFIRMED");
        }

        // give back the old quantities, then check the new ones
        increaseStock(order.getItems());
        List<OrderItem> newItems;
        try {
            newItems = buildItems(request.items());
        } catch (RuntimeException ex) {
            decreaseStock(order.getItems());
            throw ex;
        }

        setItemsAndTotals(order, newItems);
        decreaseStock(newItems);
        return orderRepository.save(order);
    }

    public Order confirm(Long id) {
        Order order = findOrder(id);
        if (order.getStatus() == OrderStatus.CONFIRMED) {
            throw new BusinessException("Order is already confirmed");
        }
        order.setStatus(OrderStatus.CONFIRMED);
        return orderRepository.save(order);
    }

    public Order pay(Long id) {
        Order order = findOrder(id);
        if (order.isPaid()) {
            throw new BusinessException("Order is already paid");
        }
        order.setPaid(true);
        return orderRepository.save(order);
    }

    private List<OrderItem> buildItems(List<OrderItemRequest> requestItems) {
        // if the same product is sent twice we add the quantities together
        Map<Long, Integer> quantities = new LinkedHashMap<>();
        for (OrderItemRequest item : requestItems) {
            quantities.merge(item.productId(), item.quantity(), Integer::sum);
        }

        List<OrderItem> items = new ArrayList<>();
        for (Map.Entry<Long, Integer> entry : quantities.entrySet()) {
            StoreProduct product = productRepository.findById(entry.getKey())
                    .orElseThrow(() -> new NotFoundException("Product " + entry.getKey() + " not found"));
            int quantity = entry.getValue();

            if (!product.isActive()) {
                throw new BusinessException(product.getName() + " is not available anymore");
            }
            if (quantity > product.getStock()) {
                throw new BusinessException("Only " + product.getStock() + " left of " + product.getName());
            }

            // always use the current price from the product, not from the request
            double lineTotal = product.getPrice() * quantity;
            items.add(new OrderItem(product.getId(), product.getName(), quantity, product.getPrice(), round(lineTotal)));
        }
        return items;
    }

    private void setItemsAndTotals(Order order, List<OrderItem> items) {
        double subtotal = items.stream().mapToDouble(OrderItem::getLineTotal).sum();
        double shippingFee = subtotal >= FREE_SHIPPING_LIMIT ? 0 : SHIPPING_FEE;

        order.setItems(items);
        order.setSubtotal(round(subtotal));
        order.setShippingFee(shippingFee);
        order.setTotal(round(subtotal + shippingFee));
    }

    private void decreaseStock(List<OrderItem> items) {
        for (OrderItem item : items) {
            StoreProduct product = productRepository.findById(item.getProductId()).orElseThrow();
            product.setStock(product.getStock() - item.getQuantity());
            productRepository.save(product);
        }
    }

    private void increaseStock(List<OrderItem> items) {
        for (OrderItem item : items) {
            StoreProduct product = productRepository.findById(item.getProductId()).orElseThrow();
            product.setStock(product.getStock() + item.getQuantity());
            productRepository.save(product);
        }
    }

    private Order findOrder(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order not found"));
    }
}
