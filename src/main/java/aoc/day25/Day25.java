package aoc.day25;

import aoc.Day;
import aoc.util.AocTools;

import java.util.ArrayList;
import java.util.List;

public class Day25 implements Day {

    public String runPartOne(boolean testInput) {
        List<PinHolder> locksAndKeys = getLocksAndKeys(AocTools.read(false, this));
        List<Key> keys = locksAndKeys.stream().filter(s -> s instanceof Key).map(s -> (Key) s).toList();
        List<Lock> locks = locksAndKeys.stream().filter(s -> s instanceof Lock).map(s -> (Lock) s).toList();
        int count = 0;
        for (Key key: keys) {
            for (Lock lock: locks) {
                if (key.canFit(lock)) {
                    count++;
                }
            }
        }
        return "" + count;
    }

    @Override
    public String runPartTwo(boolean testInput) {
        return "";
    }

    public boolean mustPrint() {
        return false;
    }


    public List<PinHolder> getLocksAndKeys(List<String> input) {
        List<PinHolder> result = new ArrayList<>();
        for (List<String> lockOrKey: AocTools.getGroups(input)) {
            if (lockOrKey.get(0).equals("#####")) {
                result.add(new Lock(lockOrKey.subList(1, lockOrKey.size())));
            } else {
                result.add(new Key(lockOrKey.subList(0, lockOrKey.size()-1)));
            }
        }
        return result;
    }

}
