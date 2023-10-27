package domain.model;

import org.springframework.util.StreamUtils;

public record CustomerReference(String value) {

    public CustomerReference {
        if (value == null) {
            throw new IllegalArgumentException("Null values are not valid Customer reference values");
        }

        boolean doesMatchPattern = value.matches("EKW[0-9]{8}");
        if (!doesMatchPattern) {
            throw new IllegalArgumentException(
                "%s does not follow expected Customer Reference format [EKW + 8 numeric characters]".formatted(
                    value));
        }
    }
}
