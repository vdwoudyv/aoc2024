package aoc.day5;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;

public class ConstraintMapComparator implements Comparator<Integer> {

    private final Map<Integer, Set<Integer>> map;

    public ConstraintMapComparator(Map<Integer, Set<Integer>> input) {
        this.map = input;
    }


    @Override
    public int compare(Integer o1, Integer o2) {
        if (map.get(o1) != null && map.get(o1).contains(o2)) {
            return -1;
        } else if (map.get(o2) != null && map.get(o2).contains(o1)) {
            return 1;
        } else {
            System.out.println("Weird");
            return 0;
        }
    }
}
