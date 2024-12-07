package aoc.day7;

import java.util.ArrayList;
import java.util.List;

public class Sequence {

    private long solution;
    private long firstDigit;

    private List<List<Long>> remainderLists = new ArrayList<>();



    public Sequence(String input) {
        String[] split = input.split(":");
        solution = Long.parseLong(split[0].trim());
        String[] params = split[1].trim().split(" ");

        ArrayList<Long> all = new ArrayList<>();
        for (String p: params) {
            all.add(Long.parseLong(p.trim()));
        }
        firstDigit  = all.remove(0);
        remainderLists.add(0, all);
        List<Long> current = all.subList(1, all.size());
        while (! current.isEmpty()) {
            remainderLists.add(current);
            current = current.subList(1, current.size());
        }
    }

    public boolean isValid(boolean includeConcat) {
        return solutionFound(solution, firstDigit, remainderLists, includeConcat);
    }

    public boolean solutionFound(long targetSolution, long accSoFar, List<List<Long>> remainders, boolean includeConcat) {
        if (remainders.isEmpty()) {
            return accSoFar == targetSolution;
        } else {
            if (accSoFar > targetSolution) {
                return false;
            }
            List<List<Long>> newRemainders = remainders.subList(1, remainders.size());
            List<Long> relevantNow = remainders.get(0);
            // Addition:
            return solutionFound(targetSolution, accSoFar + relevantNow.get(0), newRemainders, includeConcat)
            || solutionFound(targetSolution, accSoFar * relevantNow.get(0), newRemainders, includeConcat)
            || (includeConcat && solutionFound(targetSolution, concat(accSoFar, relevantNow.get(0)), newRemainders, includeConcat));
        }
    }


    private long concat(long first, long second) {
        return Long.parseLong("" + first + second);
    }

    public long getSolution() {
        return solution;
    }
}
