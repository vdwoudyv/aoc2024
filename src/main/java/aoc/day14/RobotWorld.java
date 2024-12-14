package aoc.day14;

import aoc.util.Coordinate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RobotWorld {

    private final int width;
    private final int height;
    private List<Robot> robots;

    public RobotWorld(int width, int height, List<String> input) {
        this.width = width;
        this.height = height;
        this.robots = input.stream().map(s -> new Robot(s, this)).toList();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void move(int nbSeconds) {
        robots.forEach(r -> r.move(1));
    }

    public long getSafetyFactor() {
        Map<Integer, List<Robot>> quadrantMap = robots.stream().collect(Collectors.groupingBy(Robot::getQuadrant));
        long totalSafety = 1;
        for (int i = 1; i <= 4; i++) {
            totalSafety = totalSafety * quadrantMap.get(i).size();
        }
        return totalSafety;
    }


    // A world is a candidate tree if it has at least cutOff lines with in it cutOff consecutive robots.
    public boolean isCandidateChristmasTree(int cutOff) {

        Map<Integer, List<Robot>> lines = robots.stream().collect(Collectors.groupingBy(r -> r.getLocation().y()));
        int consec = 0;
        for (int i = 0; i < height; i++) {
            List<Robot> line = lines.get(i);
            int consecInLine = 0;
            int currentX = -1;
            if (line != null && line.size() > cutOff) {
                for (Robot r : line.stream().sorted().toList()) {
                    if (r.getLocation().x() == currentX + 1) {
                        consecInLine++;
                    } else {
                        consecInLine = 0;
                    }
                    currentX = r.getLocation().x();
                    if (consecInLine == cutOff) {
                        consec++;
                        break;
                    }
                }
            }
            if (consec > cutOff) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        Map<Coordinate, List<Robot>> map = robots.stream().collect(Collectors.groupingBy(r -> r.getLocation()));
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                sb.append(map.containsKey(new Coordinate(column, row)) ? "o" : ".");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
