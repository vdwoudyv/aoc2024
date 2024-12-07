package aoc.day3;

import aoc.Day;
import aoc.util.AocTools;
import aoc.util.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day3 implements Day {

    public String runPartOne(boolean testInput) {
        return "" + extractMulPairs(String.join("", AocTools.read(false, this)), false).stream()
                .mapToLong(p -> (long) p.first() * p.second()).sum();
    }

    @Override
    public String runPartTwo(boolean testInput) {
        return "" + extractMulPairs(String.join("", AocTools.read(false, this)), true).stream()
                .mapToLong(p -> (long) p.first() * p.second()).sum();
    }

    public List<Tuple<Integer, Integer>> extractMulPairs(String input, boolean obeyDoDont) {
        // Regular expression to match valid "mul(?,?)" patterns
        String regex = "(mul\\((\\d{1,3}),(\\d{1,3})\\))|do\\(\\)|don't\\(\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        boolean enabled = true;
        // List to store the extracted pairs
        List<Tuple<Integer, Integer>> pairs = new ArrayList<>();

        while (matcher.find()) {
            if (matcher.group(1) != null && enabled) {
                // Convert the matched groups to integers and add them as pairs
                int num1 = Integer.parseInt(matcher.group(2));
                int num2 = Integer.parseInt(matcher.group(3));
                pairs.add(new Tuple(num1, num2));
            } else if ("do()".equals(matcher.group())) {
                // do
                enabled = true;
            } else {
                // don't
                if (obeyDoDont) {
                    enabled = false;
                }
            }
        }
        return pairs;
    }

    public boolean mustPrint() {
        return false;
    }

}
