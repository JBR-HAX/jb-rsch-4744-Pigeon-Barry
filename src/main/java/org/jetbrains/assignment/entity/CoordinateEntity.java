package org.jetbrains.assignment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.assignment.dto.Coordinate;

@Entity(name = "coordinate")
@Table(name = "coordinate")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CoordinateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int x;

    private int y;

    public CoordinateEntity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static CoordinateEntity from(Coordinate coordinate) {
        return CoordinateEntity.builder()
            .x(coordinate.getX())
            .y(coordinate.getY())
            .build();
    }
}
