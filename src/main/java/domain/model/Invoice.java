package domain.model;

import java.util.List;
import java.util.Objects;

public record Invoice(
    Amount amount,
    List<MonthlyConsumption> monthlyConsumptions
) {

    public Invoice {
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }

        if (monthlyConsumptions == null) {
            throw new IllegalArgumentException("Monthly consumptions cannot be null");
        }

        if (monthlyConsumptions.stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Monthly consumptions cannot contains null consumption");
        }
    }
}
