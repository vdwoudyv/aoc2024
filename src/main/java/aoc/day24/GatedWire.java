package aoc.day24;

public abstract class GatedWire extends Wire {

    protected Wire first;
    protected Wire second;

    public GatedWire(String name, Wire first, Wire second) {
        super(name);
        this.first = first;
        this.second = second;
    }

    public boolean isDirectBitCombination(int level) {
        String xName = "x" + toNumberString(level);
        String yName = "y" + toNumberString(level);
        return (first.getName().equals(xName) && second.getName().equals(yName)) ||
                (first.getName().equals(yName) && second.getName().equals(xName));
    }
}
