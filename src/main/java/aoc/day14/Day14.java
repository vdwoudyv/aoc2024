package aoc.day14;

import aoc.Day;
import aoc.util.AocTools;

public class Day14 implements Day {

    public String runPartOne(boolean testInput) {
        testInput = false;
        RobotWorld world = new RobotWorld(testInput ? 11 : 101,testInput ? 7 : 103, AocTools.read(testInput, this));
        world.move(1000000);
        return "" + world.getSafetyFactor();
    }

    @Override
    public String runPartTwo(boolean testInput) {
        // We do a visual check.
        RobotWorld world = new RobotWorld(101, 103, AocTools.read(false, this));
        for (int i = 0; i < 25000; i++) {
            world.move(1);
            if (world.isCandidateChristmasTree(10)) {
                System.out.println("Candidate " + (i+1));
                System.out.println(world);
            }
        }
        return "";
    }

    public boolean mustPrint() {
        return false;
    }

}
