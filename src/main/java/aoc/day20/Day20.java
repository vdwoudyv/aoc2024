package aoc.day20;

import aoc.Day;
import aoc.util.AocTools;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day20 implements Day {

    public String runPartOne(boolean testInput) {
        RaceTrack track = new RaceTrack(AocTools.read(false, this));
        List<Cheat> cheats = track.getCheats(2);
        return "" + cheats.stream().filter(c -> c.timeSaved() >= 100).count();
    }

    @Override
    public String runPartTwo(boolean testInput) {
        RaceTrack track = new RaceTrack(AocTools.read(false, this));
        List<Cheat> cheats = track.getCheats(20);
        return "" + cheats.stream().filter(c -> c.timeSaved() >= 100).count();
    }

    public boolean mustPrint() {
        return false;
    }

}
