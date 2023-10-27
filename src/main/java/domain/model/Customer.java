package domain.model;

import java.math.BigInteger;
import java.util.List;

public interface Customer {
    CustomerReference reference();
    default Invoice invoice(List<MonthlyConsumption> monthlyConsumptions) {
        var invoiceAmount = monthlyConsumptions
            .stream()
            .map(this::price)
            .reduce( Amount.ZERO, Amount::add);

        return new Invoice(invoiceAmount, monthlyConsumptions);
    }

    default Amount price(MonthlyConsumption monthlyConsumption) {
        var pricePerKwhInTensOfCents = switch (monthlyConsumption.energyType()) {
            case GAS -> gasPriceInTensOfCents();
            case ELECTRICITY -> electricityPriceInTensOfCents();
        };

        var amountValue = BigInteger.valueOf(monthlyConsumption.consumption())
                                    .multiply(BigInteger.valueOf(pricePerKwhInTensOfCents))
                                    .divide(BigInteger.TEN);

        return new Amount(amountValue);
    }

    int gasPriceInTensOfCents();
    int electricityPriceInTensOfCents();
}
