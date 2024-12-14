package aoc.day9;

public abstract class Cell {

    private int length;

    public Cell(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public abstract long checksum(int initialLocation);

    public void setLength(int newValue) {
        this.length = newValue;
    }

    public void reduceLength(Integer length) {
        setLength(Math.max(0, this.getLength() - length));
    }
}
