package aoc.util;

public enum Direction {
    NORTH(0, -1, "^"),
    SOUTH(0, 1, "v"),
    EAST(1, 0, ">"),
    WEST(-1, 0, "<");

    private final int diffX;
    private final int diffY;
    private String symbol;

    Direction(int diffX, int diffY, String symbol) {
        this.diffX = diffX;
        this.diffY = diffY;
        this.symbol = symbol;
    }

    public int getDiffX() {
        return diffX;
    }

    public int getDiffY() {
        return diffY;
    }

    public String getSymbol() {
        return symbol;
    }

    public Direction getOpposite() {
        return switch (this) {
            case NORTH -> SOUTH;
            case SOUTH -> NORTH;
            case EAST -> WEST;
            case WEST -> EAST;
        };
    }

    public Direction turnRight() {
        return  switch (this) {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
        };
    }

    public Direction turnLeft() {
        return switch (this) {
            case NORTH -> WEST;
            case WEST -> SOUTH;
            case SOUTH -> EAST;
            case EAST -> NORTH;
        };
    }
}
