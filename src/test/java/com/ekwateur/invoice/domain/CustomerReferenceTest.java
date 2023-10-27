package com.ekwateur.invoice.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CustomerReferenceTest {

    @Test
    void should_contain_customer_reference() {
        String referenceValue = "EKW01234567";

        var reference = new CustomerReference(referenceValue);

        assertThat(reference.value()).isEqualTo(referenceValue);
    }

    @Test
    void should_not_accept_null_values() {
        assertThatThrownBy(() -> new CustomerReference(null))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("Null values are not valid Customer reference values");
    }

    @ParameterizedTest
    @ValueSource(strings = {"FLX01234567", "EKX01234567", "EKW0123456", "EKWA123456", "EKW012A456", "EKW0123456A", "EKW012 34567", "  ", ""})
    void should_validate_reference_format(String invalidReferenceValue) {
        assertThatThrownBy(() -> new CustomerReference(invalidReferenceValue))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage(
                "%s does not follow expected Customer Reference format [EKW + 8 numeric characters]".formatted(
                    invalidReferenceValue));
    }
}
