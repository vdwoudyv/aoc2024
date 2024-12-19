package aoc.day17;

import aoc.Day;
import aoc.util.AocTools;
import aoc.util.Tuple;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Day17 implements Day {

    public String runPartOne(boolean testInput) {
        List<List<String>> groups = AocTools.getGroups(AocTools.read(false, this));
        Register r = new Register(groups.get(0));
        InputReader reader = new InputReader(groups.get(1).get(0).substring(8).trim());
        OutputLogger logger = new OutputLogger();
        CPU cpu = new CPU(reader, logger, r);
        cpu.run();
        return logger.getOutput();

    }

    @Override
    public String runPartTwo(boolean testInput) {
        List<List<String>> groups = AocTools.getGroups(AocTools.read(false, this));
        BigInteger currentA = BigInteger.ZERO;
        InputReader reader = new InputReader(groups.get(1).get(0).substring(8).trim());
        List<Integer> outputValues = new ArrayList<>(reader.getAll());
        for (int i = outputValues.size()-1; i>=0; i--) {
            BigInteger out = new BigInteger(""+ outputValues.get(i));
            BigInteger lowNewA = currentA.multiply(CPU.EIGHT);
            currentA = computeInput(lowNewA, currentA, out, reader);
        }
        return "" + currentA;
    }

    public boolean mustPrint() {
        return true;
    }


    /**
     * Compute the needed values for A so that after running the program once, the values of A and B (the output)
     * match the required values.
     * @param minRangeInitA the lowest A to try, only 8 values of A are tried, as the modulo 8 is all that matters for the output.
     * @param desiredOutA the desired outcome for A
     * @param desiredOutB the desired outcome for B
     */
    public BigInteger computeInput(BigInteger minRangeInitA, BigInteger desiredOutA, BigInteger desiredOutB, InputReader reader) {
        Register r = new Register(BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO);
        for (int i = 0; i < 8; i++) {
            BigInteger current = minRangeInitA.add(new BigInteger("" + i));
            reader.reset();
            r.resetTo(current,BigInteger.ZERO,BigInteger.ZERO);
            OutputLogger logger = new OutputLogger();
            CPU cpu = new CPU(reader, logger, r);
            cpu.runOnce();
            if (desiredOutB.equals(logger.lastLogged()) && r.getA().equals(desiredOutA)) {
                return current;
            }
            reader.reset();
        }
        return null;
    }

}
