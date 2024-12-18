package aoc.day16;

import aoc.Day;
import aoc.util.AocTools;

public class Day16 implements Day {

    public String runPartOne(boolean testInput) {
        Maze maze = new Maze(AocTools.read(false, this));
        return ""+ maze.getBestPathScore();
    }

    @Override
    public String runPartTwo(boolean testInput) {

        Maze maze = new Maze(AocTools.read(false,this));
        return "" + maze.getNbOfBestTiles();
    }

    public boolean mustPrint() {
        return false;
    }

}
