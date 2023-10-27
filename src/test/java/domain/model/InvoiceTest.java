package domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class InvoiceTest {

    @Test
    void should_throw_if_amount_is_null() {
        assertThatThrownBy(() -> new Invoice(null, List.of()))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("Amount cannot be null");
    }

    @Test
    void should_throw_if_monthly_consumptions_is_null() {
        assertThatThrownBy(() -> new Invoice(new Amount(0), null))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("Monthly consumptions cannot be null");
    }

    @Test
    void should_throw_if_monthly_consumptions_contains_null_object() {
        List<MonthlyConsumption> monthlyConsumptions = new ArrayList<>();
        monthlyConsumptions.add(new MonthlyConsumption(1, EnergyType.GAS));
        monthlyConsumptions.add(null);

        assertThatThrownBy(() -> new Invoice(new Amount(0), monthlyConsumptions))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("Monthly consumptions cannot contains null consumption");
    }
}
