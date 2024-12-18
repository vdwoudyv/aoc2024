package aoc.day15;

import aoc.util.Cell;
import aoc.util.CellWorld;
import aoc.util.Coordinate;
import aoc.util.Direction;

public abstract class Item extends Cell {

    public Item(Coordinate c, String content, CellWorld<Item> world) {
        super(c, content, world);
    }

    public void setCoordinate(Coordinate c) {
        this.c = c;
    }

    public abstract boolean canMove(Direction d);

    public RobotWorld getWorld() {
        return (RobotWorld) super.getWorld();
    }

    public boolean canMove(String direction) {
        return canMove(toDirection(direction));
    }

    private Direction toDirection(String direction) {
        return switch (direction) {
            case "v" -> Direction.SOUTH;
            case "^" -> Direction.NORTH;
            case ">" -> Direction.EAST;
            case "<" -> Direction.WEST;
            default -> throw new IllegalArgumentException("Invalid Direction");
        };
    }

    public void move(String direction) {
        move(toDirection(direction));
    }

    public abstract void move(Direction d);

    public long getGps() {
        return c.x()+c.y()*100L;
    }

    @Override
    public String toString() {
        return getContent();
    }
}
