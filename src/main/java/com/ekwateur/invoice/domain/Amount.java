package com.ekwateur.invoice.domain;

import java.math.BigInteger;

public record Amount(BigInteger valueInCents) {

    public static final Amount ZERO = new Amount(0);

    public Amount {
        if (valueInCents == null) {
            throw new IllegalArgumentException("Amount valueInCents cannot be null");
        }

        if (valueInCents.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("Amount valueInCents cannot be negative");
        }
    }

    public Amount(long valueInCents) {
        this(BigInteger.valueOf(valueInCents));
    }

    public Amount add(Amount amount) {
        return new Amount(amount.valueInCents.add(this.valueInCents));
    }

    public boolean isStriclyBelow(Amount other) {
        return this.valueInCents.compareTo(other.valueInCents) < 0;
    }
}
