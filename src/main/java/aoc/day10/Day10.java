package aoc.day10;

import aoc.util.AocTools;

import java.util.function.Function;

public class Day10 implements aoc.Day {

    public String runPartOne(boolean testInput) {
        World w = new World(AocTools.parseAsArray(AocTools.read(false, this), Function.identity()));
        return "" + w.getCells().values().stream().filter(c->c.getValue() == 0).mapToLong(c->c.getReachableTops().size()).sum();
    }

    @Override
    public String runPartTwo(boolean testInput) {
        World w = new World(AocTools.parseAsArray(AocTools.read(false, this), Function.identity()));
        return "" + w.getCells().values().stream().filter(c -> c.getValue() == 0).mapToLong(Cell::getNbPaths).sum();
    }

    public boolean mustPrint() {
        return false;
    }

}
