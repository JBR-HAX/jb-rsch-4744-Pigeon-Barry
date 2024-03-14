package org.jetbrains.assignment;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.jetbrains.assignment.dto.Coordinate;
import org.jetbrains.assignment.dto.Step;
import org.jetbrains.assignment.service.MoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MovementController {

    private final MoveService moveService;

    @PostMapping("/locations")
    public ResponseEntity<List<Coordinate>> locations(
        @RequestBody @Valid @NotEmpty List<Step> steps
    ) {
        return ResponseEntity.ok(moveService.locations(steps));
    }

    @PostMapping("/moves")
    public ResponseEntity<List<Step>> moves(
        @RequestBody @Valid @NotEmpty List<Coordinate> coordinates
    ) {
        return ResponseEntity.ok(moveService.moves(coordinates));
    }
}
