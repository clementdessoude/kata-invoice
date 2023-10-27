package com.ekwateur.invoice.domain;

public record PrivateIndividual(
    CustomerReference reference,
    Civility civility,
    String lastName,
    String firstName
) implements Customer {

    public PrivateIndividual {
        if (reference == null) {
            throw new IllegalArgumentException(
                "Customer Reference should be provided");
        }

        if (civility == null) {
            throw new IllegalArgumentException("Civility should be provided");
        }

        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name should be provided");
        }

        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First name should be provided");
        }
    }

    @Override
    public int gasPriceInTensOfCents() {
        return 115;
    }

    @Override
    public int electricityPriceInTensOfCents() {
        return 121;
    }
}
