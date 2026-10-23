package org.example.businesslogic.discount.repository;

import org.example.businesslogic.discount.model.DiscountCode;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Repository
public class DiscountCodeRepository {

    private final Map<String, DiscountCode> codes = new HashMap<>();

    public DiscountCodeRepository() {
        add(new DiscountCode("SAVE10", 10, Set.of("WELCOME15")));
        add(new DiscountCode("WELCOME15", 15, Set.of("SAVE10", "BLACKFRIDAY")));
        add(new DiscountCode("BLACKFRIDAY", 20, Set.of("WELCOME15")));
        add(new DiscountCode("EXTRA5", 5, Set.of()));
    }

    public Optional<DiscountCode> findByCode(String code) {
        return Optional.ofNullable(codes.get(code.toUpperCase()));
    }

    public List<DiscountCode> findAll() {
        return List.copyOf(codes.values());
    }

    private void add(DiscountCode discountCode) {
        codes.put(discountCode.getCode(), discountCode);
    }
}
