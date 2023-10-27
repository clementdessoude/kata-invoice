package domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class AmountTest {
    @Test
    void should_throw_if_provided_amount_is_null() {
        assertThatThrownBy(() -> new Amount(null))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("Amount value cannot be null");
    }

    @Test
    void should_throw_if_provided_amount_is_negative() {
        assertThatThrownBy(() -> new Amount(new BigDecimal(-1)))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("Amount value cannot be negative");
    }

    @Test
    void should_contains_amounts() {
        var amount = new Amount(new BigDecimal(1));

        assertThat(amount.value()).isEqualTo(BigDecimal.ONE);
    }

    @Test
    void should_create_amounts_from_long() {
        var amount = new Amount(1);

        assertThat(amount.value()).isEqualTo(BigDecimal.ONE);
    }
}
