package aoc.day9;

public class NumberCell extends Cell {

    private Integer value;

    public NumberCell(Integer value, Integer length) {
        super(length);
        this.value = value;
    }

    @Override
    public String toString() {
        String symbol = "" + (value % 10);
        return symbol.repeat(getLength());
    }

    @Override
    public long checksum(int startLocation) {
        long result = 0;
        for (int i = 0; i < getLength(); i++) {
            result += value * (long) (i+startLocation);
        }
        return result;
    }

    public Integer getValue() {
        return value;
    }

    public Space empty() {
        return new Space(this.getLength());
    }
}
