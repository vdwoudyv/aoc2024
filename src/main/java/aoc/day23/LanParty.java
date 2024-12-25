package aoc.day23;

import aoc.util.AocTools;

import java.util.*;
import java.util.stream.Stream;

public class LanParty {

    Map<String, Set<String>> network = new HashMap<>();

    public LanParty(List<String> input) {
        for (String connection: input) {
            String[] parts = connection.split("-");
            network.computeIfAbsent(parts[0], k -> new HashSet<>()).add(parts[1]);
            network.computeIfAbsent(parts[1], k -> new HashSet<>()).add(parts[0]);
        }
    }

    public List<List<String>> getTriplets(boolean mustStartWithT) {
        List<String> allPcs = mustStartWithT
                ? new ArrayList<>(network.keySet().stream().filter(s -> s.startsWith("t")).toList())
                : new ArrayList<>(network.keySet());
        List<List<String>> result = new ArrayList<>();
        for (String first : allPcs) {
            for (String second : network.get(first)) {
                Set<String> common = AocTools.intersect(network.get(first), network.get(second));
                for (String commonConnection : common) {
                    List<String> triplet = Stream.of(first, second, commonConnection).sorted().toList();
                    if (!result.contains(triplet)) {
                        result.add(triplet);
                    }
                }
            }
        }
        return result;
    }

    public List<String> getLargestLanParty() {
        SubsetFinder finder = new SubsetFinder(network);
        return finder.findLargestSubset().stream().sorted().toList();
    }
}
