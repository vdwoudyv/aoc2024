package aoc.day24;

import java.math.BigInteger;
import java.util.*;

public class GateSimulation {

    Map<String, Wire> wireMap = new HashMap<>();
    List<Wire> outputWires = new ArrayList<>();

    public GateSimulation(List<String> initialValues, List<String> gates) {
        for (String val: initialValues) {
            addInitial(val);
        }
        List<List<String>> parsedGates = new ArrayList<>(gates.stream().map(this::parseConnection).toList());

        while (!parsedGates.isEmpty()) {
            List<List<String>> toAdd = parsedGates.stream()
                    .filter(g -> wireMap.containsKey(g.get(2)) && wireMap.containsKey(g.get(3))).toList();
            for (List<String> element: toAdd) {
                parsedGates.remove(element);
                addConnection(element);
            }
        }
    }

    public void printOut() {
        for (int i = 0; i < 46; i++) {
            final int iVal = i;
            Optional<Wire> w = wireMap.values().stream().filter(s -> s.isValidAdder(iVal)).findFirst();
            if (w.isPresent()) {
                    System.out.println("Level " + i + ": " + w.get().getName());
            }
        }
    }

    private void addConnection(List<String> element) {
        Wire wire = switch (element.get(1)) {
            case "XOR" -> new WireAfterXor(element.get(0), wireMap.get(element.get(2)), wireMap.get(element.get(3)));
            case "OR" -> new WireAfterOr(element.get(0), wireMap.get(element.get(2)), wireMap.get(element.get(3)));
            case "AND" -> new WireAfterAnd(element.get(0), wireMap.get(element.get(2)), wireMap.get(element.get(3)));
            default -> throw new IllegalArgumentException("Unexpected operator");
        };
        wireMap.put(wire.getName(), wire);
        if (wire.getName().startsWith("z")) {
            outputWires.add(wire);
        }
    }


    // returns List of: destinationname, operation, input one, input 2
    private List<String> parseConnection(String connection) {
        String[] parts = connection.split(" ");
        return List.of(parts[4], parts[1], parts[0], parts[2]);
    }

    private void addInitial(String value) {
        String[] parts = value.split(": ");
        boolean initialValue = Integer.parseInt(parts[1]) == 1;
        wireMap.put(parts[0], new WireWithFixedInput(parts[0], initialValue));
    }

    public BigInteger getDecimalNumber() {
        BigInteger current = new BigInteger("0");
        Collections.sort(outputWires);
        for (int i = outputWires.size() -1; i >=0; i--) {
            boolean value = outputWires.get(i).getInput();
            current = current.multiply(BigInteger.TWO).add(value ? BigInteger.ONE : BigInteger.ZERO);
        }
        return current;
    }
}
