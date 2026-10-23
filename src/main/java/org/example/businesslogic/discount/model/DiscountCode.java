package org.example.businesslogic.discount.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiscountCode {

    private String code;
    private double percent;
    private Set<String> incompatibleCodes;

    public boolean canBeUsedWith(DiscountCode other) {
        return !incompatibleCodes.contains(other.getCode())
                && !other.getIncompatibleCodes().contains(code);
    }
}
