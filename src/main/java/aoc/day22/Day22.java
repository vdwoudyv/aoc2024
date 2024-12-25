package aoc.day22;

import aoc.Day;
import aoc.util.AocTools;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day22 implements Day {

    public String runPartOne(boolean testInput) {
        SecretNumberGenerator sng = new SecretNumberGenerator();
        return "" +  AocTools.read(false, this)
                .stream()
                .map(Long::parseLong)
                .mapToLong(b -> sng.generateNth(b, 2000))
                .sum();
    }

    @Override
    public String runPartTwo(boolean testInput) {
        SecretNumberGenerator sng = new SecretNumberGenerator();
        List<Map<String, Integer>> result = AocTools.read(false, this)
                .stream().map(Long::parseLong)
                .map(l -> sng.calculateSequenceToPriceMap(l, 2000))
                .toList();
        return "" + getMaxBananas(result);
    }

    public boolean mustPrint() {
        return false;
    }

    public long getMaxBananas(List<Map<String, Integer>> sequenceMaps) {
        Set<String> allSequences = new HashSet<>();
        for (Map<String, Integer> map: sequenceMaps) {
            allSequences.addAll(map.keySet());
        }
        String bestSequence = null;
        long maxBananas = 0L;

        for (String sequence: allSequences) {
            long totalBananasForSequence = 0L;
            for (Map<String, Integer> map: sequenceMaps) {
                totalBananasForSequence += map.getOrDefault(sequence, 0);
            }
            if (totalBananasForSequence > maxBananas) {
                bestSequence = sequence;
                maxBananas = totalBananasForSequence;
            }
        }
        System.out.println("Best sequence: " + bestSequence);
        return maxBananas;
    }

}
