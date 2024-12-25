package aoc.day23;

import aoc.Day;
import aoc.util.AocTools;

import java.util.List;
import java.util.stream.Collectors;

public class Day23 implements Day {

    public String runPartOne(boolean testInput) {
        LanParty party = new LanParty(AocTools.read(false, this));
        return "" + party.getTriplets(true).size();
    }

    @Override
    public String runPartTwo(boolean testInput) {
        LanParty party = new LanParty(AocTools.read(false, this));
        System.out.println("This takes some time (about 2 or 3 minutes)");
        return "" + String.join(",", party.getLargestLanParty());
    }

    public boolean mustPrint() {
        return false;
    }

}
