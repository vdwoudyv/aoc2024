package aoc.day21;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirectionalKeypadOperator extends KeypadOperator {

    public DirectionalKeypadOperator() {
        // Here there are simply no options, so we hardcode.
        conversionMap.put("<^", ">^"); // >>^< is idiotic and unlikely
        conversionMap.put("<v",">");
        conversionMap.put("<>", ">>"); // >^>v is idiotic and unlikely
        conversionMap.put("^A", ">");
        conversionMap.put("^<", "v<"); // >v<< is idiotic and unlikely
        conversionMap.put("^v", "v");
        conversionMap.put("v<", "<");
        conversionMap.put("v>", ">");
        conversionMap.put("v^", "^");
        conversionMap.put(">A", "^");
        conversionMap.put("><", "<<");
        conversionMap.put(">v", "<");
        conversionMap.put("A^", "<");
        conversionMap.put("A>", "v");
    }

    public static List<DirectionalKeypadOperator> getConfigurations() {
        List<String> leftA = List.of(">>^", ">^>");
        List<String> upRight = List.of("v>", ">v");
        List<String> downA = List.of(">^", "^>");
        List<String> rightUp = List.of("<^", "^<");
        List<String> aLeft = List.of("v<<", "<v<");
        List<String> aDown = List.of("<v", "v<");
        List<DirectionalKeypadOperator> result = new ArrayList<>();
        for (String leftAValue: leftA) {
            for (String upRightValue: upRight) {
                for (String downAValue: downA) {
                    for (String rightUpValue: rightUp) {
                        for (String aLeftValue: aLeft) {
                            for (String aDownValue: aDown) {
                                DirectionalKeypadOperator dko = new DirectionalKeypadOperator();
                                dko.conversionMap.put("<A", leftAValue);
                                dko.conversionMap.put("^>", upRightValue); // >v
                                dko.conversionMap.put("vA", downAValue); // ^>
                                dko.conversionMap.put(">^", rightUpValue); // ^<
                                dko.conversionMap.put("A<", aLeftValue);// <v<
                                dko.conversionMap.put("Av", aDownValue); // v<
                                result.add(dko);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }


    public static DirectionalKeypadOperator getBestConfiguration() {
        long lowest = Long.MAX_VALUE;
        DirectionalKeypadOperator bestSoFar = null;
        for (DirectionalKeypadOperator dko: getConfigurations()) {
            // any input will do I think
            // after a few passes, the influence of non-optimal choices will become visible.
            String input = "<v<A>>^A<vA<A>>^AAvAA<^A>A<v<A>>^AAvA^A<vA>^AA<A>A<v<A>A>^AAAvA<^A>A";
            for (int i = 0; i < 5; i++) {
                input = dko.generateInput(input);
            }
            if (input.length() < lowest) {
                bestSoFar = dko;
                lowest = input.length();
            }
        }
        return bestSoFar;
    }
}
