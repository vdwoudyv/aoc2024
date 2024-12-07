package aoc.day4;

import aoc.Day;
import aoc.util.AocTools;
import aoc.util.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 implements Day {

    public String runPartOne(boolean testInput) {
        List<List<String>> parsed = AocTools.parseAsArray(AocTools.read(false, this), (s) -> s);
        return "" + countXMas(parsed, false);
    }

    // Count occurrences of the word XMAS
    private long countXMas(List<List<String>> parsed, boolean countDoubleMas) {
        long total = 0;
        for (int row = 0; row < parsed.size(); row ++) {
            for (int column = 0; column < parsed.get(0).size(); column++) {
                total += countDoubleMas ? countDoubleMas(row, column, parsed) : countXMas(row, column, parsed);
            }
        }
        return total;
    }

    /**
     * Count the number of occurances of the word XMAS that start at the given location
     * @param row
     * @param column
     * @param parsed
     * @return
     */
    private long countXMas(int row, int column, List<List<String>> parsed) {
        if (parsed.get(row).get(column).equals("X")) {
            List<String> words = new ArrayList<>();
            words.add(getWord(row, column, parsed, -1, -1,4));
            words.add(getWord(row, column, parsed, 0, -1,4));
            words.add(getWord(row, column, parsed, 1, -1,4));
            words.add(getWord(row, column, parsed, 1, 0,4));
            words.add(getWord(row, column, parsed, 1, 1,4));
            words.add(getWord(row, column, parsed, 0, 1,4));
            words.add(getWord(row, column, parsed, -1, 1,4));
            words.add(getWord(row, column, parsed, -1, 0,4));
            return words.stream().filter(s -> s.equals("XMAS")).count();
        } else {
            return 0L;
        }
    }

    private long countDoubleMas(int row, int column, List<List<String>> parsed) {
        if (parsed.get(row).get(column).equals("A")) {
            List<String> words = new ArrayList<>();
            String diagFirst = getWord(row-1, column-1, parsed, 1, 1,3);
            words.add(diagFirst);
            words.add(new StringBuilder(diagFirst).reverse().toString());
            String diagSecond = getWord(row+1, column-1, parsed, -1, 1,3);
            words.add(diagSecond);
            words.add(new StringBuilder(diagSecond).reverse().toString());
            if (words.stream().filter(s -> s.equals("MAS")).count() == 2) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return 0L;
        }
    }



    private String getWord(int row, int column, List<List<String>> input, int rowDirection, int columnDirection, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            int rowCoord = row + rowDirection*i;
            if (rowCoord >= input.size() || rowCoord < 0) {
                return "";
            }
            int columnCoord = column + columnDirection*i;
            if (columnCoord >= input.get(0).size() || columnCoord < 0) {
                return "";
            }
            sb.append(input.get(rowCoord).get(columnCoord));
        }
        return sb.toString();
    }

    @Override
    public String runPartTwo(boolean testInput) {
        List<List<String>> parsed = AocTools.parseAsArray(AocTools.read(false, this), (s) -> s);
        return "" + countXMas(parsed, true);
    }

    public boolean mustPrint() {
        return false;
    }

}
