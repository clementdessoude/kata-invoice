package domain.model;

import java.math.BigDecimal;

public record Amount(BigDecimal value) {

    public Amount {
        if (value == null) {
            throw new IllegalArgumentException("Amount value cannot be null");
        }

        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount value cannot be negative");
        }
    }

    public Amount(long value) {
        this(new BigDecimal(value));
    }
}
