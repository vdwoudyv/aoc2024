package aoc.day24;

import aoc.Day;
import aoc.util.AocTools;

import java.util.List;

public class Day24 implements Day {

    public String runPartOne(boolean testInput) {
        List<List<String>> groups = AocTools.getGroups(AocTools.read(false, this));
        GateSimulation gs = new GateSimulation(groups.get(0), groups.get(1));
        return "" + gs.getDecimalNumber();
    }

    @Override
    public String runPartTwo(boolean testInput) {
        // Currently this code does not print the solution.
        // What it does is: it starts validating the name of the node giving the correct resultbit at index i
        // If all is correct, you should get z_i for each i from 0-44
        // If it is not, it will halt where it sees a problem.
        // If it prints something else instead of z_i, for example: Level 7: vmv, it means I have to switch z_i with vmv
        // in the inputfile, as vmv is the correct result.
        // if it prints nothing, you have to look at the z of the first level that does not validate. Use the AdderLogic.png
        // to see what the structure would need to be. You don't have to go deep, as all previous adders and carrybits
        // are correct.

        // Changed is the same as Input.txt, but with the corrections applied.
        List<List<String>> groups = AocTools.getGroups(AocTools.read("Changed.txt", this));
        GateSimulation gs = new GateSimulation(groups.get(0), groups.get(1));
        gs.printOut();
        return "";


    }
    //gs.wireMap.values().stream().filter(w -> w.isValidCarry(1)).toList();

    public boolean mustPrint() {
        return true;
    }

}
