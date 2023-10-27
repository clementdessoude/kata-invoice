package domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class LegalEntityTest {

    @Test
    void should_throw_if_no_customer_reference_is_provided() {
        assertThatThrownBy(
            () -> new LegalEntity(
                null,
                new Siret("123"),
                "Flaubert Company",
                new Amount(1_000_000_00)
            )
        ).isExactlyInstanceOf(IllegalArgumentException.class)
         .hasMessage("Customer Reference should be provided");
    }

    @Test
    void should_throw_if_no_siret_is_provided() {
        assertThatThrownBy(
            () -> new LegalEntity(
                new CustomerReference("EKW01234567"),
                null,
                "Flaubert Company",
                new Amount(1_000_000_00)
            )
        ).isExactlyInstanceOf(IllegalArgumentException.class)
         .hasMessage("Siret should be provided");
    }

    @ParameterizedTest
    @NullAndEmptySource
    void should_throw_if_no_last_name_is_provided(String companyName) {
        assertThatThrownBy(
            () -> new LegalEntity(
                new CustomerReference("EKW01234567"),
                new Siret("123"),
                companyName,
                new Amount(1_000_000_00)
            )
        ).isExactlyInstanceOf(IllegalArgumentException.class)
         .hasMessage("Company name should be provided");
    }

    @Test
    void should_throw_if_no_turnover_is_provided() {
        assertThatThrownBy(
            () -> new LegalEntity(
                new CustomerReference("EKW01234567"),
                new Siret("123"),
                "Flaubert Company",
                null
            )
        ).isExactlyInstanceOf(IllegalArgumentException.class)
         .hasMessage("Turnover should be provided");
    }

    @Test
    void should_compute_invoice_for_electricity_for_legal_entity_below_threshold() {
        List<MonthlyConsumption> consumptions = List.of(
            new MonthlyConsumption(10_000, EnergyType.ELECTRICITY)
        );
        var legalEntity = new LegalEntity(
            new CustomerReference("EKW01234567"),
            new Siret("123"),
            "Flaubert Company",
            new Amount(999_999_99)
        );

        var invoice = legalEntity.invoice(consumptions);

        assertThat(invoice).isEqualTo(
            new Invoice(new Amount(1_180_00), consumptions)
        );
    }

    @Test
    void should_compute_invoice_for_gas_for_legal_entity_below_threshold() {
        List<MonthlyConsumption> consumptions = List.of(
            new MonthlyConsumption(10_000, EnergyType.GAS)
        );
        var privateIndividual = new LegalEntity(
            new CustomerReference("EKW01234567"),
            new Siret("123"),
            "Flaubert Company",
            new Amount(999_999_99)
        );

        var invoice = privateIndividual.invoice(consumptions);

        assertThat(invoice).isEqualTo(
            new Invoice(new Amount(1_130_00), consumptions)
        );
    }

    @Test
    void should_compute_invoice_for_electricity_for_legal_entity_above_threshold() {
        List<MonthlyConsumption> consumptions = List.of(
            new MonthlyConsumption(10_000, EnergyType.ELECTRICITY)
        );
        var legalEntity = new LegalEntity(
            new CustomerReference("EKW01234567"),
            new Siret("123"),
            "Flaubert Company",
            new Amount(1_000_000_00)
        );

        var invoice = legalEntity.invoice(consumptions);

        assertThat(invoice).isEqualTo(
            new Invoice(new Amount(1_140_00), consumptions)
        );
    }

    @Test
    void should_compute_invoice_for_gas_for_legal_entity_above_threshold() {
        List<MonthlyConsumption> consumptions = List.of(
            new MonthlyConsumption(10_000, EnergyType.GAS)
        );
        var privateIndividual = new LegalEntity(
            new CustomerReference("EKW01234567"),
            new Siret("123"),
            "Flaubert Company",
            new Amount(1_000_000_00)
        );

        var invoice = privateIndividual.invoice(consumptions);

        assertThat(invoice).isEqualTo(
            new Invoice(new Amount(1_110_00), consumptions)
        );
    }
}
