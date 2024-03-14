package service;

import org.jetbrains.assignment.dto.Coordinate;
import org.jetbrains.assignment.dto.Direction;
import org.jetbrains.assignment.dto.Step;
import org.jetbrains.assignment.repository.LocationRepository;
import org.jetbrains.assignment.service.MoveServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class MoveServiceImplTest {

    private MoveServiceImpl moveService;
    private LocationRepository locationRepository;

    @BeforeEach
    void beforeAll() {
        this.locationRepository = mock(LocationRepository.class);
        this.moveService = new MoveServiceImpl(locationRepository);
    }

    @Nested
    @DisplayName("public List<Coordinate> locations(List<Step> steps)")
    class Locations {
        @Test
        public void positiveLocationsEmptySteps() {
            List<Coordinate> coordinates = moveService.locations(
                List.of(
                    new Step(Direction.EAST, 1),
                    new Step(Direction.NORTH, 3),
                    new Step(Direction.EAST, 3),
                    new Step(Direction.SOUTH, 5),
                    new Step(Direction.WEST, 2)
                )
            );
            assertEquals(List.of(
                    new Coordinate(0, 0),
                    new Coordinate(1, 0),
                    new Coordinate(1, 3),
                    new Coordinate(4, 3),
                    new Coordinate(4, -2),
                    new Coordinate(2, -2)
                ), coordinates,
                "The coordinates are not as expected");
            verify(locationRepository, times(1)).save(any());
        }
    }

    @Nested
    @DisplayName("public List<Step> moves(List<Coordinate> coordinates)")
    class Moves {
        @Test
        public void positiveMovesEmptyCoordinates() {
            List<Step> steps = moveService.moves(
                List.of(
                    new Coordinate(0, 0),
                    new Coordinate(1, 0),
                    new Coordinate(1, 3),
                    new Coordinate(0, 3),
                    new Coordinate(0, 0)
                )
            );
            assertEquals(List.of(
                    new Step(Direction.EAST, 1),
                    new Step(Direction.NORTH, 3),
                    new Step(Direction.WEST, 1),
                    new Step(Direction.SOUTH, 3)
                ), steps,
                "The steps are not as expected");
            verify(locationRepository, times(1)).save(any());
        }
    }
}
