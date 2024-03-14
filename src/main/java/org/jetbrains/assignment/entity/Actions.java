package org.jetbrains.assignment.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "action")
@Table(name = "action")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Actions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<CoordinateEntity> coordinates;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<StepEntity> steps;

    private Type type;


    public enum Type {
        LOCATION,
        MOVE
    }
}
