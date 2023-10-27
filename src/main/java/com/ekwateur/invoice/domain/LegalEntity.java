package com.ekwateur.invoice.domain;

public record LegalEntity(
    CustomerReference reference,
    Siret siret,
    String name,
    Amount turnover

) implements Customer{

    private static final Amount TURNOVER_PRICING_THRESHOLD = new Amount(
        1_000_000_00);

    public LegalEntity {
        if (reference == null) {
            throw new IllegalArgumentException("Customer Reference should be provided");
        }

        if (siret == null) {
            throw new IllegalArgumentException("Siret should be provided");
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Company name should be provided");
        }

        if (turnover == null) {
            throw new IllegalArgumentException("Turnover should be provided");
        }
    }

    @Override
    public int gasPriceInTensOfCents() {
        if (turnover.isStriclyBelow(TURNOVER_PRICING_THRESHOLD)) {
            return 113;
        }
        return 111;
    }

    @Override
    public int electricityPriceInTensOfCents() {
        if (turnover.isStriclyBelow(TURNOVER_PRICING_THRESHOLD)) {
            return 118;
        }
        return 114;
    }
}
