package domain.model;

import java.math.BigInteger;

public record Amount(BigInteger valueInCents) {

    public Amount {
        if (valueInCents == null) {
            throw new IllegalArgumentException("Amount valueInCents cannot be null");
        }

        if (valueInCents.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("Amount valueInCents cannot be negative");
        }
    }

    public Amount(long value) {
        this(BigInteger.valueOf(value));
    }
}
