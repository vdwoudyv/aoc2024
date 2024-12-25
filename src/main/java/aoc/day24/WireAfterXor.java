package aoc.day24;

public class WireAfterXor extends GatedWire {


    public WireAfterXor(String name, Wire first, Wire second) {
        super(name, first, second);
    }

    public boolean getInput() {
        return first.getInput() ^ second.getInput();
    }

    @Override
    public boolean isValidAdder(int level) {
        // One part should be an xOr of the level input bits
        // Other part should be a valid cary bit of the given level
        if (first instanceof WireAfterXor && !(second instanceof WireAfterXor)) {
            // first should be the addition, second the carry
            return first.isDirectBitCombination(level) && second.isValidCarry(level);
        } else if (second instanceof WireAfterXor && !(first instanceof WireAfterXor)) {
            return second.isDirectBitCombination(level) && first.isValidCarry(level);
        } else {
            return false;
        }
    }

    @Override
    public boolean isValidCarry(int level) {
        return false;
    }
}
