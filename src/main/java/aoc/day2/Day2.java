package aoc.day2;

import aoc.Day;
import aoc.util.AocTools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day2 implements Day {

    public String runPartOne(boolean testInput) {
        return "" + AocTools.read(false, this).stream().map(Report::new).filter(Report::isSafe).count();
    }

    @Override
    public String runPartTwo(boolean testInput) {
        return "" + AocTools.read(false, this).stream().map(Report::new).filter(Report::isSafeWithRemoval).count();
    }

    public boolean mustPrint() {
        return false;
    }

}
