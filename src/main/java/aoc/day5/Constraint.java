package aoc.day5;

import java.util.List;

public class Constraint {

    private final int first;
    private final int second;

    public Constraint(String s) {
        String[] parts = s.split("\\|");
        first = Integer.parseInt(parts[0]);
        second = Integer.parseInt(parts[1]);
    }

    public boolean violates(List<Integer> input) {
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i) == first && input.subList(0, i).contains(second)) {
                return true;
            }
        }
        return false;
    }

    public boolean isRelevant(List<Integer> page) {
        return page.contains(first) && page.contains(second);
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }
}
