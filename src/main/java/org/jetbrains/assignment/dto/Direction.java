package org.jetbrains.assignment.dto;

public enum Direction {
    EAST, WEST, NORTH, SOUTH;


    public Coordinate move(Coordinate coordinate, int steps) {
       return switch (this) {
           case EAST -> new Coordinate(coordinate.getX() + steps, coordinate.getY());
           case WEST -> new Coordinate(coordinate.getX() - steps, coordinate.getY());
           case NORTH -> new Coordinate(coordinate.getX(), coordinate.getY() + steps);
           case SOUTH -> new Coordinate(coordinate.getX(), coordinate.getY() - steps);
       };
    }
}
