package edu.hw10.models.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;

public record RecordWithAllTypes(
    @NotNull String stringValue,
    @Min("10") int intValue,
    long longValue,
    @Max("3.1415") double doubleValue,
    @Min("0.5") @Max("2.5") float floatValue
) { }
