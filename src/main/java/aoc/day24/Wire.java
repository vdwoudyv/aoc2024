package aoc.day24;

public abstract class Wire implements Comparable<Wire> {

    private String name;

    public Wire(String name) {
        this.name = name;
    }

    public abstract boolean getInput();

    @Override
    public String toString() {
        boolean val = getInput();
        return name + ": " + (val ? "1" : "0");
    }

    @Override
    public int compareTo(Wire o) {
        return name.compareTo(o.name);
    }

    public String getName() {
        return name;
    }

    public abstract boolean isValidAdder(int level);

    public abstract boolean isValidCarry(int level);

    public abstract boolean isDirectBitCombination(int level);

    public String toNumberString(int number) {
        return ((number < 10) ? "0" : "" ) + number;

    }
}
