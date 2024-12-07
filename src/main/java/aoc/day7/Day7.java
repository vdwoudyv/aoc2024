package aoc.day7;

import aoc.Day;
import aoc.util.AocTools;

public class Day7 implements Day {

    public String runPartOne(boolean testInput) {
        return "" + AocTools.read(false, this).stream().map(Sequence::new).filter(s -> s.isValid(false)).mapToLong(Sequence::getSolution).sum();
    }

    @Override
    public String runPartTwo(boolean testInput) {
        return "" + AocTools.read(false, this).stream().map(Sequence::new).filter(s -> s.isValid(true)).mapToLong(Sequence::getSolution).sum();
    }

    public boolean mustPrint() {
        return false;
    }

}
