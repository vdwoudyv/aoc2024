package aoc.day20;

import aoc.util.Coordinate;

public record Cheat(Coordinate from, Coordinate to, int shortCut) implements Comparable<Cheat> {

    @Override
    public int compareTo(Cheat o) {
        return o.shortCut - shortCut;
    }


    public static boolean cheatPossible(Coordinate c1, Coordinate c2, int maxPicoSecondsAllowed) {
        int hor = Math.abs(c1.x() - c2.x());
        int ver = Math.abs(c1.y() - c2.y());
        int lengthOfCheat = hor + ver;
        return lengthOfCheat <= maxPicoSecondsAllowed && (hor + ver > 0);
    }

    public int timeSaved() {
        return shortCut - length();
    }

    public int length() {
        int hor = Math.abs(from.x() - to.x());
        int ver = Math.abs(from.y() - to.y());
        return hor + ver;
    }
}
