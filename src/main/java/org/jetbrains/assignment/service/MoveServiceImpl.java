package org.jetbrains.assignment.service;

import lombok.RequiredArgsConstructor;
import org.jetbrains.assignment.dto.Coordinate;
import org.jetbrains.assignment.dto.Direction;
import org.jetbrains.assignment.dto.Step;
import org.jetbrains.assignment.entity.Actions;
import org.jetbrains.assignment.entity.CoordinateEntity;
import org.jetbrains.assignment.entity.StepEntity;
import org.jetbrains.assignment.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MoveServiceImpl implements MoveService {

    private final LocationRepository locationRepository;

    @Override
    public List<Coordinate> locations(List<Step> steps) {
        Coordinate currentLocation = new Coordinate(0, 0);
        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(currentLocation);
        for (Step step : steps) {
            currentLocation = step.getDirection().move(currentLocation, step.getSteps());
            coordinates.add(currentLocation);
        }
        locationRepository.save(Actions.builder()
            .coordinates(
                coordinates.stream()
                    .map(CoordinateEntity::from)
                    .toList()
            )
            .steps(steps.stream()
                .map(StepEntity::from)
                .toList())
            .type(Actions.Type.LOCATION)
            .build());
        return coordinates;
    }

    @Override
    public List<Step> moves(List<Coordinate> coordinates) {
        List<Step> stepsMade = new ArrayList<>();
        if (coordinates == null || coordinates.isEmpty()) {
            return stepsMade;
        }
        Coordinate currentLocation = coordinates.get(0);
        for (int i = 1; i < coordinates.size(); i++) {
            Coordinate nextLocation = coordinates.get(i);
            int x = nextLocation.getX() - currentLocation.getX();
            int y = nextLocation.getY() - currentLocation.getY();
            Direction direction;
            int steps;
            if (x > 0) {
                direction = Direction.EAST;
                steps = x;
            } else if (x < 0) {
                direction = Direction.WEST;
                steps = -x;
            } else if (y > 0) {
                direction = Direction.NORTH;
                steps = y;
            } else if (y < 0) {
                direction = Direction.SOUTH;
                steps = -y;
            } else {
                continue;
            }
            stepsMade.add(new Step(direction, steps));
            currentLocation = nextLocation;
        }
        locationRepository.save(Actions.builder()
            .coordinates(
                coordinates.stream()
                    .map(CoordinateEntity::from)
                    .toList()
            )
            .steps(stepsMade.stream()
                .map(StepEntity::from)
                .toList())
            .type(Actions.Type.LOCATION)
            .build());
        return stepsMade;
    }
}
