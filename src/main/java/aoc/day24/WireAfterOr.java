package aoc.day24;

public class WireAfterOr extends GatedWire {


    public WireAfterOr(String name, Wire first, Wire second) {
        super(name, first, second);
    }

    public boolean getInput() {
        return first.getInput() || second.getInput();
    }

    @Override
    public boolean isValidAdder(int level) {
        return false;
    }

    @Override
    public boolean isValidCarry(int level) {
        if (level >= 2) {
            if (isGeneratedCarryBitAtLevel(level, first)) {
                return combinesAdditionAndCarry(level-1, second);
            } else if (isGeneratedCarryBitAtLevel(level, second)) {
                return combinesAdditionAndCarry(level-1, first);
            }
        }
        return false;
    }

    private boolean combinesAdditionAndCarry(int level, Wire toCheck) {
        if (toCheck instanceof WireAfterAnd andWire) {
            return andWire.combinesAdditionAndCarry(level);
        } else {
            return false;
        }
    }

    private boolean isGeneratedCarryBitAtLevel(int level, Wire toCheck) {
        return (toCheck instanceof WireAfterAnd) && toCheck.isDirectBitCombination(level - 1);
    }
}
