package aoc.day12;


import aoc.util.Cell;
import aoc.util.CellWorld;
import aoc.util.Coordinate;
import aoc.util.Direction;

import java.util.Optional;


public class FarmCell extends Cell {

    private Patch patch;
    private int perimeter;

    public FarmCell(Coordinate c, String content, CellWorld world) {
        super(c, content, world);
    }

    public Optional<FarmCell> getNeighbour(Direction d) {
        return Optional.ofNullable((FarmCell) (getWorld().getCell(getCoordinate().getAdjacent(d))));
    }

    public Patch getPatch() {
        return patch;
    }

    public void scan() {
        for (Direction d: Direction.values()) {
            Optional<FarmCell> neighbour = getNeighbour(d);
            neighbour.ifPresent(nb ->
                    {
                        if (!nb.getContent().equals(getContent())) {
                            perimeter++;
                        }
                        if (patch == null && nb.getPatch() != null && getContent().equals(nb.getContent())) {
                            nb.getPatch().addCell(this);
                        } else if (patch != null && nb.getPatch() != null && patch != nb.getPatch() &&  getContent().equals(nb.getContent())) {
                            getPatch().merge(nb.patch);
                        }
                    });
            if (neighbour.isEmpty()) {
                perimeter++;
            }
        }
        if (this.patch == null) {
            this.patch = new Patch(this);
        }
    }

    public void setPatch(Patch patch) {
        this.patch = patch;
    }

    public int getPerimeter() {
        return perimeter;
    }
}
