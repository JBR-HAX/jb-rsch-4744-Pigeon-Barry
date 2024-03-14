package org.jetbrains.assignment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.assignment.dto.Direction;
import org.jetbrains.assignment.dto.Step;

@Entity(name = "step")
@Table(name = "step")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StepEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Enumerated
    private Direction direction;

    private int steps;

    public StepEntity(Direction direction, int steps) {
        this.direction = direction;
        this.steps = steps;
    }

    public static StepEntity from(Step step) {
        return StepEntity.builder()
            .direction(step.getDirection())
            .steps(step.getSteps())
            .build();
    }
}
