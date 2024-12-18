package aoc.day15;

import aoc.util.CellWorld;
import aoc.util.Coordinate;
import aoc.util.Direction;

import java.util.ArrayList;
import java.util.List;

public class Box extends Item {

    private int width = 1;

    public Box(Coordinate c, CellWorld<Item> world) {
        this(c, world, 1);
    }

    public Box(Coordinate c, CellWorld<Item> world, int width) {
        super(c, "O", world);
        this.width = width;
    }

    @Override
    public boolean canMove(Direction d) {
        List<Item> neighbours = getNeighbours(d);
        return neighbours.stream().allMatch(n->n.canMove(d));
    }

    private List<Item> getNeighbours(Direction d) {
        List<Item> neighbours = new ArrayList<>();
        if (d == Direction.WEST || width == 1) {
            Item neighbour = getWorld().getCell(getCoordinate().getAdjacent(d));
            if (neighbour != null) {
                neighbours.add(neighbour);
            }
        } else if (d == Direction.EAST) {
            Item neighbour = getWorld().getCell(getCoordinate().getAdjacent(d).getAdjacent(d));
            if (neighbour != null) {
                neighbours.add(neighbour);
            }
        }else {
            for (int i = 0; i < width; i++) {
                Coordinate coord = new Coordinate(c.x()+i, c.y());
                Item neighbour = getWorld().getCell(coord.getAdjacent(d));
                if (neighbour != null && ! neighbours.contains(neighbour)) {
                    neighbours.add(neighbour);
                }
            }
        }
        return neighbours;
    }

    @Override
    public void move(Direction d) {
        if (canMove(d)) {
            List<Item> neighbours = getNeighbours(d);
            if (neighbours.isEmpty()) {
                getWorld().updatePosition(this, c.getAdjacent(d));
            } else {
                for (Item currentNeighbour: neighbours) {
                    currentNeighbour.move(d);
                }
                getWorld().updatePosition(this, c.getAdjacent(d));
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public String toString() {
        return width == 1 ? "O" : "[]";
    }

    public List<Coordinate> getAllCoordinates() {
        List<Coordinate> result = new ArrayList<>();
        for (int i = 0; i < width;i++) {
            result.add(new Coordinate(c.x()+i, c.y()));
        }
        return result;
    }
}
