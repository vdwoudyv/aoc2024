package aoc.day17;

import aoc.Day;
import aoc.util.AocTools;

import java.util.List;

public class Day17 implements Day {

    public String runPartOne(boolean testInput) {
        List<List<String>> groups = AocTools.getGroups(AocTools.read(true, this));
        Register r = new Register(groups.get(0));
        InputReader reader = new InputReader(groups.get(1).get(0).substring(8).trim());
        OutputLogger logger = new OutputLogger(reader, false);
        CPU cpu = new CPU(reader, logger, r);
        cpu.run();
        return logger.getOutput();
    }

    @Override
    public String runPartTwo(boolean testInput) {
        List<List<String>> groups = AocTools.getGroups(AocTools.read(false, this));
        Register r = new Register(groups.get(0));
        InputReader reader = new InputReader(groups.get(1).get(0).substring(8).trim());
        OutputLogger logger = new OutputLogger(reader, false);
        CPU cpu = new CPU(reader, logger, r);
        return "" + cpu.runTilEquivalent();
    }

    public boolean mustPrint() {
        return false;
    }

}
