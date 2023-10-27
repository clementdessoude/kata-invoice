package com.ekwateur.invoice.domain;

public record Siret(String value) {
    public Siret {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Siret value should be provided");
        }
    }
}
