package org.jetbrains.assignment.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@Data
@ToString
public class Step {
    @NotNull
    private Direction direction;
    @Min(0)
    private int steps;

    public Step(Direction direction, int steps) {
        this.direction = direction;
        this.steps = steps;
    }


}
