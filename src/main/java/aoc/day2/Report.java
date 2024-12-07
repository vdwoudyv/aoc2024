package aoc.day2;

import aoc.util.Tuple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Report {

    private List<Integer> values;

    public Report(String input) {
        values = Arrays.stream(input.split(" ")).map(Integer::parseInt).toList();
    }

    public Report(List<Integer> clone) {
        this.values = clone;
    }

    public boolean isSafe() {
        Tuple<Integer, int[]> diffs = getDiffs();
        boolean uniform = Arrays.stream(diffs.second()).noneMatch(value -> Math.signum(value) != Math.signum(diffs.first()) || value == 0);
        boolean tooLarge = Arrays.stream(diffs.second()).anyMatch(value -> Math.abs(value) > 3);
        return uniform && !tooLarge;
    }

    public boolean isSafeWithRemoval() {
        Tuple<Integer, int[]> diffs = getDiffs();
        int dominantSign = diffs.first();
        int[] values = diffs.second();

        for (int i = 0; i < values.length; i ++) {
            int value = values[i];
            if (Math.signum(value) != dominantSign || value == 0 || Math.abs(value) > 3) {
                // Als een transitie fout is, dan moet je ofwel de eerste, ofwel de tweede verwijderen.
                List<Integer> firstAlternative = new ArrayList<>(this.values);
                firstAlternative.remove(i);
                List<Integer> secondAlternative = new ArrayList<>(this.values);
                secondAlternative.remove(i+1);
                return new Report(firstAlternative).isSafe() || new Report(secondAlternative).isSafe();
            }
        }
        return true;
    }


    private Tuple<Integer, int[]> getDiffs() {
        int neg = 0;
        int pos = 0;
        int[] diffs = new int[values.size() - 1];
        for (int i = 0; i<  values.size() - 1; i++) {
            int diff = values.get(i+1) - values.get(i);
            if (diff > 0) {
                pos++;
            } else {
                neg++;
            };
            diffs[i] = diff;
        }
        return new Tuple<>(neg > pos ? -1 : 1, diffs);
    }
}
