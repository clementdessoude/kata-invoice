package com.ekwateur.invoice.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class SiretTest {

    @ParameterizedTest
    @NullAndEmptySource
    void should_throw_if_no_siret_is_provided(String value) {
        assertThatThrownBy(() -> new Siret(value))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("Siret value should be provided");
    }

    @Test
    void should_represent_siret() {
        var siret = new Siret("123");

        assertThat(siret.value()).isEqualTo("123");
    }
}
