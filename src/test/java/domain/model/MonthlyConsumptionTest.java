package domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class MonthlyConsumptionTest {

    @Test
    void should_throw_if_consumption_is_negative() {
        assertThatThrownBy(() -> new MonthlyConsumption(-1, EnergyType.GAS))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("Consumption cannot be negative");
    }

    @Test
    void should_throw_if_energy_type_is_not_provided() {
        assertThatThrownBy(() -> new MonthlyConsumption(1, null))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("Energy type should be provided");
    }
}
