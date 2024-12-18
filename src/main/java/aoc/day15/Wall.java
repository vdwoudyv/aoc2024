package aoc.day15;

import aoc.util.CellWorld;
import aoc.util.Coordinate;
import aoc.util.Direction;

public class Wall extends Item {

    public Wall(Coordinate c, CellWorld<Item> world) {
        super(c, "#", world);
    }

    @Override
    public boolean canMove(Direction d) {
        return false;
    }

    @Override
    public void move(Direction d) {
    }
}
