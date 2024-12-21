package aoc.day21;

import aoc.Day;
import aoc.util.AocTools;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day21 implements Day {

    public String runPartOne(boolean testInput) {
        List<String> inputNumbers = AocTools.read(false, this);
        int result = 0;
        for (String code : inputNumbers) {
            String movements = execute(code, 2);
            int size = movements.length();
            int numericalPart = Integer.parseInt(code.replaceAll("A", ""));
            result += size * numericalPart;
        }
        return "" + result;
    }

    @Override
    public String runPartTwo(boolean testInput) {
        List<String> inputNumbers = AocTools.read(false, this);
        BigInteger result = BigInteger.ZERO;
        for (String code : inputNumbers) {
            BigInteger size = nbOfStrokes(code, 25);
            BigInteger numericalPart = new BigInteger(code.replaceAll("A", ""));
            result = result.add(size.multiply(numericalPart));
        }
        return "" + result;
    }

    private String execute(String code, int nbIntermediaries) {
        DirectionalKeypadOperator operator = DirectionalKeypadOperator.getBestConfiguration();
        NumericalKeypadOperator numerical = new NumericalKeypadOperator();
        String input = numerical.generateInput(code);
        String result = input;
        for (int i = 0; i < nbIntermediaries; i++) {
            result = operator.generateInput(result);
        }
        return result;
    }

    private BigInteger nbOfStrokes(String code, int nbIntermediaries) {
        DirectionalKeypadOperator operator = DirectionalKeypadOperator.getBestConfiguration();
        NumericalKeypadOperator numerical = new NumericalKeypadOperator();
        String input = numerical.generateInput(code);

        Map<String, BigInteger> occuranceMap = new HashMap<>();
        List<String> parts = splitSequence(input);
        for (String s : parts) {
            occuranceMap.put(s, BigInteger.ONE);
        }
        for (int i = 0; i < nbIntermediaries; i++) {
            Map<String, BigInteger> newMap = new HashMap<>();
            for (String subsequence : occuranceMap.keySet()) {
                BigInteger nbOccurancesOfSubSequence = occuranceMap.get(subsequence);
                List<String> newParts = splitSequence(operator.generateInput(subsequence));
                for (String part : newParts) {
                    BigInteger nb = newMap.getOrDefault(part, BigInteger.ZERO);
                    newMap.put(part, nb.add(nbOccurancesOfSubSequence));
                }
            }
            occuranceMap = newMap;
        }
        return occuranceMap.entrySet().stream()
                .map(e -> e.getValue().multiply(new BigInteger("" + e.getKey().length())))
                .reduce(BigInteger.ZERO, BigInteger::add);
    }

    private List<String> splitSequence(String s) {
        List<String> result = new ArrayList<>();
        String remainder = s;
        while (remainder.length() > 8) {
            for (int i = 7; i >= 0; i--) {
                if (remainder.charAt(i) == 'A') {
                    result.add(remainder.substring(0, i + 1));
                    remainder = remainder.substring(i + 1);
                    break;
                }
            }
        }
        result.add(remainder);
        return result;
    }

    public boolean mustPrint() {
        return true;
    }
}
