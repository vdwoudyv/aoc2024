package aoc.day6;

import aoc.Day;
import aoc.util.AocTools;
import aoc.util.Coordinate;

public class Day6 implements Day {

    public String runPartOne(boolean testInput) {
        Room room = new Room(AocTools.parseAsArray(AocTools.read(true, this), s -> s));
        Guard guard = new Guard(room);
        guard.walk();
        return "" + guard.nbAreas();
    }

    // Dumb brute forcing
    @Override
    public String runPartTwo(boolean testInput) {
        Room room = new Room(AocTools.parseAsArray(AocTools.read(false, this), s -> s));
        Guard guard = new Guard(room);
        int count = 0;
        for (int i = 0; i < room.getWidth(); i++) {
            for (int j = 0; j < room.getHeight(); j++) {
                if (! room.isObstacle(i,j) && ! (room.getStartColumn() == j && room.getStartRow() == i))
                {
                    room.setAdditionalObstacle(new Coordinate(i, j));
                    boolean cycle = guard.walk();
                    if (cycle) {
                        count++;
                    }
                }
            }
        }
        return "" + count;
    }

    public boolean mustPrint() {
        return false;
    }

}
