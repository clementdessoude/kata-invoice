package domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
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
}
