package aoc.day15;

import aoc.Day;
import aoc.util.AocTools;

import java.util.List;

public class Day15 implements Day {

    public String runPartOne(boolean testInput) {

        List<List<String>> input = AocTools.getGroups(AocTools.read(false, this));
        RobotWorld world = new RobotWorld();
        AocTools.parseWorld(input.get(0), (content, coordinate, w) -> world.createItem(content, coordinate), world);

        for (String direction: input.get(1)) {
            world.excuteMoves(direction);
        }
        return "" + world.getSumGPS();
    }

    @Override
    public String runPartTwo(boolean testInput) {
        List<List<String>> input = AocTools.getGroups(AocTools.read(false, this));
        RobotWorld world = new RobotWorld();
        AocTools.parseWorld(input.get(0), (content, coordinate, w) -> world.createItem(content, coordinate), world);
        world.widen();

        for (String direction: input.get(1)) {
            world.excuteMoves(direction);
        }
        return "" + (world.getSumGPS()/2);
    }

    public boolean mustPrint() {
        return false;
    }

}
