package aoc.day9;

public class Cell {

    private Integer value;
    private Integer length;
    private boolean isNumber;
    private Integer beginIndex;

    public Cell(Integer value, Integer length, boolean isNumber, Integer beginIndex) {
        this.value = value;
        this.length = length;
        this.isNumber = isNumber;
        this.beginIndex = beginIndex;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i =0; i < length; i++) {
            sb.append(isNumber ? (value % 10) : ".");   // this is just for visualisation, the actual value is not printed
        }
        return sb.toString();
    }

    public long checksum() {
        long result = 0;
        if (isNumber()) {
            for (int i = beginIndex; i < beginIndex + length; i++) {
                result += ((long) i * value);
            }
        }
        return result;
    }


    public void reduceLength(Integer length) {
        this.length = Math.max(0, this.length - length);
    }

    public Integer getValue() {
        return value;
    }

    public Integer getLength() {
        return length;
    }

    public boolean isNumber() {
        return isNumber && length > 0;
    }

    public Integer getBeginIndex() {
        return beginIndex;
    }

    public void fill(Integer value) {
        this.value = value;
        this.isNumber = true;
    }

    public void setBeginIndex(Integer beginIndex) {
        this.beginIndex = beginIndex;
    }

    public void increaseLength(Cell current) {
        length += current.getLength();
    }
}
