package aoc.day17;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OutputLogger {

    private List<BigInteger> output;


    public OutputLogger() {
        output = new ArrayList<>();
    }

    public void log(BigInteger outputNumber) {
        output.add(outputNumber);
    }

    public String getOutput() {
        return output.stream().map(l -> "" + l).collect(Collectors.joining(","));
    }

    public void reset() {
        output.clear();;
    }

    public BigInteger lastLogged() {
        if (! output.isEmpty()) {
            return output.get(output.size() - 1);
        } else {
            return null;
        }
    }
}
