package aoc.day8;

import aoc.Day;
import aoc.util.AocTools;

import java.util.List;
import java.util.function.Function;

public class Day8 implements Day {

    public String runPartOne(boolean testInput) {
        City city = new City(AocTools.parseAsArray(AocTools.read(false, this), Function.identity()));
        return "" + city.findAntinodes(1).size();
    }

    @Override
    public String runPartTwo(boolean testInput) {
        City city = new City(AocTools.parseAsArray(AocTools.read(false, this), Function.identity()));
        return "" + city.findAntinodes(100000000).size();
    }

    public boolean mustPrint() {
        return true;
    }

}
