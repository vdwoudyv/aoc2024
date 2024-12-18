package aoc.day17;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OutputLogger {

    private List<Integer> output;

    private List<Integer> expectedResult;
    private boolean allwaysPass;

    public OutputLogger(InputReader reader, boolean shouldVerify) {
        expectedResult = reader.getAll();
        output = new ArrayList<>();
        allwaysPass = ! shouldVerify;
    }

    public boolean log(Integer outputNumber) {
        output.add(outputNumber);
        boolean verified = output.size() <= expectedResult.size() && outputNumber.equals(expectedResult.get(output.size()-1));
        return allwaysPass || verified;
    }

    public String getOutput() {
        return output.stream().map(l -> "" + l).collect(Collectors.joining(","));
    }

    public void reset() {
        output.clear();;
    }

    public boolean valid() {
        boolean sameLength = expectedResult.size() == output.size();
        if (sameLength) {
            for (int i = 0; i < expectedResult.size(); i++) {
                if (!output.get(i).equals(expectedResult.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
