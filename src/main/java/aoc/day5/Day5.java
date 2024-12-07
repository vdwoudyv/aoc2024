package aoc.day5;

import aoc.Day;
import aoc.util.AocTools;
import aoc.util.Tuple;

import java.util.*;

public class Day5 implements Day {

    public String runPartOne(boolean testInput) {
        Tuple<List<Constraint>, List<? extends List<Integer>>> input = readConstraintsAndPages(testInput);
        int sum = 0;
        for (List<Integer> page : input.second()) {
            boolean violations = input.first().stream().anyMatch(c -> c.violates(page));
            if (!violations) {
                sum += page.get(page.size() / 2);
            }
        }
        return "" + sum;
    }

    @Override
    public String runPartTwo(boolean testInput) {
        Tuple<List<Constraint>, List<? extends List<Integer>>> input = readConstraintsAndPages(testInput);
        int sum = 0;
        for (List<Integer> page : input.second()) {
            boolean violations = input.first().stream().anyMatch(c -> c.violates(page));
            if (violations) {
                List<Integer> sortedPage = new ArrayList<>(page);
                sortedPage.sort(new ConstraintMapComparator(constructConstraintMap(input.first().stream().filter(c->c.isRelevant(page)).toList())));
                sum += sortedPage.get(sortedPage.size()/2);
            }
        }
        return "" + sum;
    }

    private Map<Integer, Set<Integer>> constructConstraintMap(List<Constraint> constraints) {
        Map<Integer, Set<Integer>> constraintMap = new HashMap<>();
        for (Constraint c: constraints) {
            int first = c.getFirst();
            int second = c.getSecond();
            Set<Integer> existing = constraintMap.computeIfAbsent(first, k -> new HashSet<>());
            existing.add(second);

            // If first < second, it is also smaller than all elements larger than second
            Set<Integer> transitive = constraintMap.get(second);
            if (transitive != null) {
                existing.addAll(transitive);
            }
            for (Set<Integer> values : constraintMap.values()) {
                if (values.contains(first)) {
                    values.add(second);
                }
            }
        }
        return constraintMap;
    }

    public Tuple<List<Constraint>, List<? extends List<Integer>>> readConstraintsAndPages(boolean testFile) {
        List<List<String>> input = AocTools.getGroups(AocTools.read(testFile, this));
        List<Constraint> constraints = input.get(0).stream().map(Constraint::new).toList();
        List<? extends List<Integer>> pagesToProduce =
                input.get(1).stream().map(s -> new ArrayList<>(Arrays.stream(s.split(",")).map(Integer::parseInt).toList())).toList();
        return new Tuple<>(constraints, pagesToProduce);
    }

    public boolean mustPrint() {
        return false;
    }
}
