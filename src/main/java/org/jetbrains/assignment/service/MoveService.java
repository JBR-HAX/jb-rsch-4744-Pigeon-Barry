package org.jetbrains.assignment.service;

import org.jetbrains.assignment.dto.Coordinate;
import org.jetbrains.assignment.dto.Step;

import java.util.List;

public interface MoveService {
    List<Coordinate> locations(List<Step> steps);

    List<Step> moves(List<Coordinate> coordinates);
}
