package aoc.day22;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SecretNumberGenerator {

    public long calculateNext(long seed) {
        long next = prune(mix(seed * 64L, seed));
        next = prune(mix(next / 32L , next));
        next = prune(mix(next * 2048L, next));
        return next;
    }

    private long mix(long givenValue, long secretValue) {
        return givenValue ^ secretValue;
    }

    private long prune(long secret) {
        return secret % 16777216L;

    }

    public Map<String, Integer> calculateSequenceToPriceMap(long seed, int nb) {
        Map<String, Integer> sequenceToPrice = new HashMap<>();
        List<Integer> currentSequence = new ArrayList<>();
        long currentSecret = seed;
        int currentPrice = (int) currentSecret % 10;

        for (int i = 0; i < nb; i++) {
            currentSecret = calculateNext(currentSecret);

            int newPrice = (int) currentSecret % 10;
            int diff = newPrice - currentPrice;
            currentSequence.add(diff);
            if (currentSequence.size() > 4) {
                currentSequence.remove(0);
            }
            currentPrice = newPrice;
            if (currentSequence.size() == 4) {
                String stringRep = sequenceToString(currentSequence);
                if (!sequenceToPrice.containsKey(stringRep)) {
                    sequenceToPrice.put(stringRep, currentPrice);
                }
            }
        }
        return sequenceToPrice;
    }

    public long generateNth(long seed, int nb) {
        long result = seed;
        for (int i = 0; i < nb; i++) {
            result = calculateNext(result);
        }
        return result;
    }

    private String sequenceToString(List<Integer> currentSequence) {
        return currentSequence.stream().map(i->"" + i).collect(Collectors.joining(""));
    }
}
