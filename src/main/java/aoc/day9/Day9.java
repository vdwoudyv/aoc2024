package aoc.day9;

import aoc.Day;
import aoc.util.AocTools;

public class Day9 implements Day {

    public String runPartOne(boolean testInput) {
        AocFileSystem aocFileSystem = new AocFileSystem(AocTools.read(false, this).get(0));
        aocFileSystem.compact();
        return "" + aocFileSystem.checksum();
    }

    @Override
    public String runPartTwo(boolean testInput) {
        AocFileSystem aocFileSystem = new AocFileSystem(AocTools.read(false, this).get(0));
        aocFileSystem.compactFullFiles();
        return "" + aocFileSystem.checksum();
    }

    public boolean mustPrint() {
        return true;
    }

}
