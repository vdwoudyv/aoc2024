package aoc.day9;

public class Space extends Cell {

    public Space(int length) {
        super(length);
    }

    public long checksum(int startingAt) {
        return 0L;
    }

    public String toString() {
        return ".".repeat(getLength());
    }


    public Space merge(Space other) {
        return new Space(this.getLength() + other.getLength());
    }
}
