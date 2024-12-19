package aoc.day19;

import aoc.Day;
import aoc.util.AocTools;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day19 implements Day {

    public String runPartOne(boolean testInput) {
        return "" + getMatches(testInput).stream().filter(l -> l >0).count();
    }

    @Override
    public String runPartTwo(boolean testInput) {
        return "" + getMatches(testInput).stream().mapToLong(l->l).sum();
    }

    public boolean mustPrint() {
        return false;
    }

    public List<Long> getMatches(boolean test) {
        List<List<String>> input = AocTools.getGroups(AocTools.read(test, this));
        Map<Character, List<String>> keyWords = Arrays.stream(input.get(0).get(0).split(", ")).collect(Collectors.groupingBy(s -> s.charAt(0)));
        List<String> candidates = input.get(1);
        Map<String, Long> cache = new HashMap<>();
        return candidates.stream().map(s -> nbMatches(s, keyWords, cache)).toList();
    }

    public long nbMatches(String input, Map<Character, List<String>> candidates, Map<String, Long> cache) {
        if (input.isEmpty()) {
            return 1L;
        } else if (cache.get(input) != null) {
            return cache.get(input);
        } else {
            List<String> possibleMatches = candidates.get(input.charAt(0));
            if (possibleMatches == null) {
                cache.put(input, 0L);
                return 0L;
            }
            long total = 0;
            for (String possibleMatch: possibleMatches) {
                if (input.startsWith(possibleMatch)) {
                    total += nbMatches(input.substring(possibleMatch.length()), candidates, cache);
                }
                cache.put(input, total);
            }
            return total;
        }
    }
}
