package aoc.day18;

import aoc.Day;
import aoc.util.AocTools;
import aoc.util.Coordinate;

import java.util.List;

public class Day18 implements Day {

    public String runPartOne(boolean testInput) {
        testInput = false;
        int size = testInput ? 7 : 71;
        MemorySpace ms = new MemorySpace(
                AocTools.read(testInput, this).stream().map(Coordinate::fromString).toList(), size, size);
        for (int i = 0; i < 1024; i++) {
            ms.corrupt();
        }
        return "" + ms.nbSteps();
    }

    @Override
    public String runPartTwo(boolean testInput) {
        testInput = false;
        List<String> input = AocTools.read(testInput, this);
        int size = testInput ? 7 : 71;
        MemorySpace ms = new MemorySpace(
                input.stream().map(Coordinate::fromString).toList(), size, size);
        for (int i = 0; i < input.size(); i++) {
            Coordinate corrupted = ms.corrupt();
            int result = ms.nbSteps();
            if (result == 0) {
                return "" + corrupted;
            }
        }
        return "";
    }

    public boolean mustPrint() {
        return false;
    }

}
