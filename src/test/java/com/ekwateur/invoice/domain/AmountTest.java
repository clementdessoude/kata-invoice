package com.ekwateur.invoice.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;

class AmountTest {
    @Test
    void should_throw_if_provided_amount_is_null() {
        assertThatThrownBy(() -> new Amount(null))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("Amount valueInCents cannot be null");
    }

    @Test
    void should_throw_if_provided_amount_is_negative() {
        assertThatThrownBy(() -> new Amount(BigInteger.valueOf(-1)))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("Amount valueInCents cannot be negative");
    }

    @Test
    void should_contains_amounts() {
        var amount = new Amount(BigInteger.ONE);

        assertThat(amount.valueInCents()).isEqualTo(BigInteger.ONE);
    }

    @Test
    void should_create_amounts_from_long() {
        var amount = new Amount(1);

        assertThat(amount.valueInCents()).isEqualTo(BigInteger.ONE);
    }

    @Test
    void should_add_amounts() {
        var first = new Amount(1);
        var second = new Amount(2221);

        assertThat(first.add(second)).isEqualTo(new Amount(2222));
    }
}
