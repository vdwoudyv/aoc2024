package aoc.day15;

import aoc.util.CellWorld;
import aoc.util.Coordinate;
import aoc.util.Direction;

public class Robot extends Item {
    public Robot(Coordinate c, CellWorld<Item> world) {
        super(c, "@", world);
    }

    @Override
    public boolean canMove(Direction d) {
        Item neighbour = getWorld().getCell(getCoordinate().getAdjacent(d));
        if (neighbour == null) {
            return true;
        } else {
            return neighbour.canMove(d);
        }
    }

    @Override
    public void move(Direction d) {
        if (canMove(d)) {
            Item neighbour = getWorld().getCell(getCoordinate().getAdjacent(d));
            if (neighbour == null) {
                getWorld().updatePosition(this, c.getAdjacent(d));
            } else {
                neighbour.move(d);
                getWorld().updatePosition(this, c.getAdjacent(d));
            }
        }
    }
}
