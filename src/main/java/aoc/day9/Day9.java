package aoc.day9;

import aoc.Day;
import aoc.util.AocTools;

import java.util.Arrays;
import java.util.List;

public class Day9 implements Day {

    public String runPartOne(boolean testInput) {
/*
        AocFileSystem data = new AocFileSystem(AocTools.parseAsArray(
                AocTools.read(false, this),
                Integer::parseInt,
                s -> Arrays.stream(s.split("")).toList()).get(0));
        data.compactFragmenting();
        return "" + data.computeHash();
        */

        return "";
    }

    @Override
    public String runPartTwo(boolean testInput) {
        AocFileSystem data = new AocFileSystem(AocTools.parseAsArray(
                AocTools.read(false, this),
                Integer::parseInt,
                s -> Arrays.stream(s.split("")).toList()).get(0));
        data.compactWholeBlocks();
        return "" + data.computeHash();
    }

    public boolean mustPrint() {
        return false;
    }

}
