package aoc.day13;

import aoc.Day;
import aoc.util.AocTools;

import java.math.BigDecimal;
import java.util.List;

public class Day13 implements Day {

    public String runPartOne(boolean testInput) {
        List<Equation> equations = AocTools.getGroups(AocTools.read(false, this)).stream()
                .map(l -> new Equation(l, BigDecimal.ZERO)).toList();
        return "" + equations.stream().filter(e -> e.hasSolution(100)).mapToLong(Equation::getCost).sum();
    }

    @Override
    public String runPartTwo(boolean testInput) {
        BigDecimal increment = new BigDecimal("10000000000000");
        List<Equation> equations = AocTools.getGroups(AocTools.read(false, this)).stream()
                .map(l -> new Equation(l, increment)).toList();
        return "" + equations.stream().filter(e -> e.hasSolution(null)).mapToLong(Equation::getCost).sum();
    }

    public boolean mustPrint() {
        return false;
    }

}
