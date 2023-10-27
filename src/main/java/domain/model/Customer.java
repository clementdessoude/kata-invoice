package domain.model;

import java.util.List;

public interface Customer {
    CustomerReference reference();

    Invoice invoice(List<MonthlyConsumption> monthlyConsumptions);
}
