package domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class PrivateIndividualTest {

    @Test
    void should_throw_if_no_customer_reference_is_provided() {
        assertThatThrownBy(
            () -> new PrivateIndividual(
                null,
                Civility.FEMALE,
                "Bovary",
                "Emma"
            )
        ).isExactlyInstanceOf(IllegalArgumentException.class)
         .hasMessage("Customer Reference should be provided");
    }

    @Test
    void should_throw_if_no_civility_is_provided() {
        assertThatThrownBy(
            () -> new PrivateIndividual(
                new CustomerReference("EKW01234567"),
                null,
                "Bovary",
                "Emma"
            )
        ).isExactlyInstanceOf(IllegalArgumentException.class)
         .hasMessage("Civility should be provided");
    }

    @ParameterizedTest
    @NullAndEmptySource
    void should_throw_if_no_last_name_is_provided(String lastName) {
        assertThatThrownBy(
            () -> new PrivateIndividual(
                new CustomerReference("EKW01234567"),
                Civility.FEMALE,
                lastName,
                "Emma"
            )
        ).isExactlyInstanceOf(IllegalArgumentException.class)
         .hasMessage("Last name should be provided");
    }

    @ParameterizedTest
    @NullAndEmptySource
    void should_throw_if_no_first_name_is_provided(String firstName) {
        assertThatThrownBy(
            () -> new PrivateIndividual(
                new CustomerReference("EKW01234567"),
                Civility.FEMALE,
                "Bovary",
                firstName
            )
        ).isExactlyInstanceOf(IllegalArgumentException.class)
         .hasMessage("First name should be provided");
    }

    @Test
    void should_create_private_individual() {
        var privateIndividual = new PrivateIndividual(
            new CustomerReference("EKW01234567"),
            Civility.FEMALE,
            "Bovary",
            "Emma"
        );

        assertThat(privateIndividual.civility()).isEqualTo(Civility.FEMALE);
        assertThat(privateIndividual.reference()).isEqualTo(new CustomerReference("EKW01234567"));
        assertThat(privateIndividual.lastName()).isEqualTo("Bovary");
        assertThat(privateIndividual.firstName()).isEqualTo("Emma");
    }

    @Test
    void should_compute_invoice_for_electricity() {
        List<MonthlyConsumption> consumptions = List.of(
            new MonthlyConsumption(10_000, EnergyType.ELECTRICITY)
        );
        var privateIndividual = new PrivateIndividual(
            new CustomerReference("EKW01234567"),
            Civility.FEMALE,
            "Bovary",
            "Emma"
        );

        var invoice = privateIndividual.invoice(consumptions);

        assertThat(invoice).isEqualTo(
            new Invoice(new Amount(1_210_00), consumptions)
        );
    }
}
