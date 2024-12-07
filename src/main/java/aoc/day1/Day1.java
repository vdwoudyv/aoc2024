package aoc.day1;

import aoc.Day;
import aoc.util.AocTools;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Day1 implements Day {

    public String runPartOne(boolean testInput) {
        List<String> input = AocTools.read(false, this);
        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();
        for (String s: input) {
            String[] parts = s.split(" ");
            first.add(Integer.parseInt(parts[0]));
            second.add(Integer.parseInt(parts[parts.length-1]));
        }
        Collections.sort(first);
        Collections.sort(second);
        long distance = 0;
        for (int i = 0; i < first.size(); i++) {
            distance += Math.abs(second.get(i) - first.get(i));
        }
        return "" + distance;
    }

    @Override
    public String runPartTwo(boolean testInput) {
        List<String> input = AocTools.read(false, this);
        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();
        for (String s: input) {
            String[] parts = s.split(" ");
            first.add(Integer.parseInt(parts[0]));
            second.add(Integer.parseInt(parts[parts.length-1]));
        }
        long result = 0;
        for (int i = 0; i < first.size(); i++) {
            int firstElement = first.get(i);
            result += second.stream().filter(element -> element == firstElement).count() * firstElement;
        }

        return "" + result;
    }

    public boolean mustPrint() {
        return false;
    }

}
