package aoc.day21;

import java.util.HashMap;
import java.util.Map;

public class KeypadOperator {

    protected Map<String, String> conversionMap;

    protected KeypadOperator() {
        this.conversionMap = new HashMap<>();
    }

    /**
     * The sequence that should be inputted to get the given output
     * @param output
     * @return
     */
    public String generateInput(String output) {
        StringBuilder result = new StringBuilder();
        String[] movements = output.split("");
        for (int i = 0; i < movements.length; i++) {
            String from = i == 0 ? "A" : movements[i-1];
            String to = movements[i];
            if (!from.equals(to)) {
                result.append(conversionMap.get(from+to));
            }
            result.append("A");
        }
        return result.toString();
    }
}
