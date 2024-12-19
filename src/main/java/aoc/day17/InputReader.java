package aoc.day17;

import java.util.Arrays;
import java.util.List;

public class InputReader {

    private List<Integer> input;
    private int instructionPointer;
    private int nbReads;

    public InputReader(String input) {
        this.input = Arrays.stream(input.split(",")).map(Integer::parseInt).toList();
        nbReads = 0;
    }

    public Integer read() throws NoInputException {
        if (instructionPointer >= input.size() || nbReads > 10000000) {
            throw new NoInputException();
        } else {
            int result = input.get(instructionPointer);
            instructionPointer++;
            nbReads++;
            return result;
        }
    }

    public void setInstructionPointer(int value) {
        this.instructionPointer = value;
    }

    public void reset() {
        this.instructionPointer = 0;
        this.nbReads = 0;
    }
    public List<Integer> getAll() {
        return input;
    }

    public boolean atBegin() {
        return instructionPointer == 0;
    }
}
