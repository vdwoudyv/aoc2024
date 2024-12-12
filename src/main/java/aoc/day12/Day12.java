package aoc.day12;

import aoc.Day;
import aoc.util.AocTools;
import aoc.util.CellWorld;

import java.util.List;
import java.util.function.Function;

public class Day12 implements Day {

    public String runPartOne(boolean testInput) {
        return run(testInput, Patch::getCost);
    }

    @Override
    public String runPartTwo(boolean testInput) {
        return run(testInput, Patch::getSideCostBasedOnSides);
    }

    private String run(boolean testInput, Function<Patch, Long> costMetric) {
        CellWorld<FarmCell> world = AocTools.parseWorld(AocTools.read(testInput, this),
                (content, coordinate, w) -> new FarmCell(coordinate, content, w));

        List<FarmCell> cells = world.getCells();
        for (FarmCell cell : cells) {
            cell.scan();
        }
        List<Patch> patches = cells.stream().map(FarmCell::getPatch).distinct().toList();
        return "" + patches.stream().mapToLong(costMetric::apply).sum();

    }

    public boolean mustPrint() {
        return false;
    }

}
