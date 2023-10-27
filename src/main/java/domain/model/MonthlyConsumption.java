package domain.model;

public record MonthlyConsumption(
    long consumption,
    EnergyType energyType
) {
    public MonthlyConsumption {
        if (consumption < 0) {
            throw new IllegalArgumentException("Consumption cannot be negative");
        }

        if (energyType == null) {
            throw new IllegalArgumentException("Energy type should be provided");
        }
    }
}
