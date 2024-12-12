package aoc.day11;

import aoc.Day;
import aoc.util.AocTools;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Day11 implements Day {

    public String runPartOne(boolean testInput) {
        List<Long> numbers = new ArrayList<>(Arrays.stream(AocTools.read(false, this).get(0).split(" ")).map(Long::parseLong).toList());
        for (int i = 0; i < 25; i++) {
            numbers = expand(numbers);
        }
        return "" + numbers.size();
    }

    @Override
    public String runPartTwo(boolean testInput) {
        List<Long> numbers = new ArrayList<>(Arrays.stream(AocTools.read(false, this).get(0).split(" ")).map(Long::parseLong).toList());

        for (int i = 0; i < 25; i++) {
            numbers = expand(numbers);
        }
        Map<Long, Long> resultAfter25 = numbers.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // Value --> Count
        Map<Long, Long> resultAfter50 = new HashMap<>();
        for (Long l : resultAfter25.keySet()) {
            List<Long> temp = new ArrayList<>();
            temp.add(l);
            for (int i = 0; i < 25; i++) {
                temp = expand(temp);
            }
            // Value -> Count
            Map<Long, Long> intermediate = temp.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            for (Long element : intermediate.keySet()) {
                long val = resultAfter50.getOrDefault(element, 0L);
                val += intermediate.get(element) * resultAfter25.get(l);
                resultAfter50.put(element, val);
            }
        }

        // Value --> Count
        Map<Long, Long> resultAfter75 = new HashMap<>();
        for (Long l : resultAfter50.keySet()) {
            List<Long> temp = new ArrayList<>();
            temp.add(l);
            for (int i = 0; i < 25; i++) {
                temp = expand(temp);
            }
            // Value -> Count
            Map<Long, Long> intermediate = temp.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            for (Long element : intermediate.keySet()) {
                long val = resultAfter75.getOrDefault(element, 0L);
                val += intermediate.get(element) * resultAfter50.get(l);
                resultAfter75.put(element, val);
            }
        }
         return "" + resultAfter75.values().stream().mapToLong(l-> l).sum();
    }


    private List<Long> expand(List<Long> numbers) {
        List<Long> result = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) == 0) {
                result.add(1L);
            } else {
                long[] newDigits = split(numbers.get(i));
                if (newDigits != null) {
                    result.add(newDigits[0]);
                    result.add(newDigits[1]);
                } else {
                    result.add(numbers.get(i) * 2024L);
                }
            }
        }
        return result;
    }

    private long[] split(Long input) {
        String temp = "" + input;
        if (temp.length() % 2 == 0) {
            return new long[]{
                    Long.parseLong(temp.substring(0, temp.length() / 2)),
                    Long.parseLong(temp.substring(temp.length() / 2))
            };
        } else {
            return null;
        }
    }


    public boolean mustPrint() {
        return false;
    }
}
