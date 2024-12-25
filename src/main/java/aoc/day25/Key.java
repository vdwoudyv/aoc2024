package aoc.day25;

import java.util.ArrayList;
import java.util.List;

public class Key extends PinHolder {

    public Key(List<String> input) {
        super(input);
    }

    public boolean canFit(Lock lock) {
        boolean canfit = true;
        for (int i = 0; i < 5; i++) {
            canfit = canfit && (sizes[i] + lock.getSizeAt(i)) < 6;
        }
        return canfit;
    }
}
