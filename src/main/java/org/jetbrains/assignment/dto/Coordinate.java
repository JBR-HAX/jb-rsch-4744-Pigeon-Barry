package org.jetbrains.assignment.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Coordinate {

    @Min(0)
    private int x;
    @Min(0)
    private int y;
}
