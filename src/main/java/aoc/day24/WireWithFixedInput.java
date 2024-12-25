package aoc.day24;

public class WireWithFixedInput extends Wire {

    private boolean value;

    public WireWithFixedInput(String name, boolean value) {
        super(name);
        this.value = value;
    }

    public boolean getInput() {
        return value;
    }

    @Override
    public boolean isValidAdder(int level) {
        return false;
    }

    @Override
    public boolean isValidCarry(int level) {
        return false;
    }

    @Override
    public boolean isDirectBitCombination(int level) {
        return false;
    }
}
