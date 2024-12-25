package aoc.day25;

import java.util.List;

public abstract class PinHolder {

    protected int[] sizes = new int[5];

    protected PinHolder(List<String> input) {
        for (String s: input) {
            String[] row = s.split("");
            for (int column = 0; column < row.length; column++) {
                if (row[column].equals("#")) {
                    sizes[column]++;
                }
            }
        }
    }

    public int getSizeAt(int location) {
        return sizes[location];
    }
}
