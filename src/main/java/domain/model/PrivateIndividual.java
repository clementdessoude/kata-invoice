package domain.model;

import java.math.BigInteger;
import java.util.List;

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
    public Invoice invoice(List<MonthlyConsumption> monthlyConsumptions) {
        var invoiceAmount = monthlyConsumptions
            .stream()
            .map(PrivateIndividual::price)
            .reduce( Amount.ZERO, Amount::add);

        return new Invoice(invoiceAmount, monthlyConsumptions);
    }

    private static Amount price(MonthlyConsumption monthlyConsumption) {
        var pricePerKwhInTensOfCents = switch (monthlyConsumption.energyType()) {
            case GAS -> 115;
            case ELECTRICITY -> 121;
        };

        var amountValue = BigInteger.valueOf(monthlyConsumption.consumption())
            .multiply(BigInteger.valueOf(pricePerKwhInTensOfCents))
            .divide(BigInteger.TEN);

        return new Amount(amountValue);
    }
}
