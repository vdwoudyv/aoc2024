package aoc.day23;

import aoc.util.AocTools;

import java.util.*;

public class SubsetFinder {

    private final Map<String, Set<String>> remainingSearchSpace;
    private long start;
    public SubsetFinder(Map<String, Set<String>> input) {
        remainingSearchSpace = new HashMap<>(input);
    }

    public Set<String> findLargestSubset() {
        start = System.currentTimeMillis();
        Set<String> bestSubset = new HashSet<>();

        String candidateWithMostNeighbours = findMaxNeighbour();
        while (candidateWithMostNeighbours != null) {
            System.out.println("Remaining Size: " + remainingSearchSpace.size() + " after " + (System.currentTimeMillis() - start));
            List<String> candidates = remainingSearchSpace.get(candidateWithMostNeighbours).stream()
                    .sorted((s1, s2) -> remainingSearchSpace.get(s1).size() - remainingSearchSpace.get(s2).size()).toList();

            Set<String> bestSubSetWithCandidate = findBestSubset(Set.of(candidateWithMostNeighbours), candidates, new HashSet<>());
            if (bestSubSetWithCandidate.size() > bestSubset.size()) {
                bestSubset = bestSubSetWithCandidate;
            }
            removeAndOptimize(candidateWithMostNeighbours, bestSubset.size());
            candidateWithMostNeighbours = findMaxNeighbour();
        }
        return bestSubset;
    }

    /**
     * Preconditions of this method: each individual element in candidates can be added (ie: has connection with everyone
     * in alreadyPresent although not necessarily with each other. We also assume that the given list of candidates is
     * sorted from high number of direct neighbours to low number of direct neighbours.
     */
    private Set<String> findBestSubset(Set<String> alreadyPresent, List<String> candidates, Set<String> bestFoundSoFar) {
        if (candidates.isEmpty()) {
            return alreadyPresent;
        } else if (alreadyPresent.size() + candidates.size() <= bestFoundSoFar.size()) {
            return bestFoundSoFar;
        } else {
            Set<String> bestSoFar = new HashSet<>();
            for (String candidate : candidates) {
                Set<String> newPresent = new HashSet<>(alreadyPresent);
                newPresent.add(candidate);
                List<String> newCandidates = intersect(candidates, remainingSearchSpace.get(candidate));
                Set<String> result = findBestSubset(newPresent, newCandidates, bestSoFar);
                if (result.size() > bestSoFar.size()) {
                    bestSoFar = result;
                }
            }
            return bestSoFar;
        }
    }

    private List<String> intersect(List<String> candidates, Set<String> strings) {
        List<String> result = new ArrayList<>();
        for (String s: candidates) {
            if (strings.contains(s)) {
                result.add(s);
            }
        }
        return result;
    }


    private void removeAndOptimize(String toRemove, int currentBestSize) {
        remove(toRemove);
        List<String> furtherRemovals = remainingSearchSpace.entrySet()
                .stream()
                .filter(e -> e.getValue().size() < currentBestSize)
                .map(Map.Entry::getKey)
                .toList();
        while (!furtherRemovals.isEmpty()) {
            for (String s: furtherRemovals) {
                remove(s);
            }
            furtherRemovals = remainingSearchSpace.entrySet()
                    .stream()
                    .filter(e -> e.getValue().size() < currentBestSize)
                    .map(Map.Entry::getKey)
                    .toList();
        }
    }

    private void remove(String toRemove) {
        remainingSearchSpace.remove(toRemove);
        for (String s: remainingSearchSpace.keySet()) {
            remainingSearchSpace.get(s).remove(toRemove);
        }
    }

    private String findMaxNeighbour() {
        String best = null;
        int largestSize = 0;
        for (String s: remainingSearchSpace.keySet()) {
            Set<String> neighbours = remainingSearchSpace.get(s);
            if (neighbours.size()>largestSize) {
                best = s;
                largestSize = neighbours.size();
            }
        }
        return best;
    }
}
