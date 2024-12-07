package aoc.day6;

import aoc.util.Coordinate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {

    private final Map<Integer, List<Integer>> horizontalObstacleIndex = new HashMap<>();
    private int rowCount;
    private int columnCount;
    private int startRow;
    private int startColumn;

    private Coordinate additionalObstacle;

    public Room(List<List<String>> input) {
        rowCount = input.size();
        columnCount = input.get(0).size();
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                String location = input.get(row).get(column);
                if (location.equals("#")) {
                    List<Integer> rowList = horizontalObstacleIndex.computeIfAbsent(row, k -> new ArrayList<>());
                    rowList.add(column);
                } else if (location.equals("^")) {
                    startRow = row;
                    startColumn = column;
                }
            }
        }
    }

    public void setAdditionalObstacle(Coordinate additionalObstacle) {
        this.additionalObstacle = additionalObstacle;
    }

    public boolean isObstacle(int row, int column) {
        return horizontalObstacleIndex.getOrDefault(row, new ArrayList<>()).contains(column)
                || (additionalObstacle != null && additionalObstacle.y() == column && additionalObstacle.x() == row);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rowCount; i++) {
            List<Integer> rowObsacles = horizontalObstacleIndex.getOrDefault(i, new ArrayList<>());
            for (int j = 0; j < columnCount; j++) {
                sb.append(rowObsacles.contains(j) ? "#" : ".");
                }
            sb.append("\n");
            }
        return sb.toString();
    }

    public int getHeight() {
        return rowCount;
    }

    public int getWidth() {
        return columnCount;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getStartColumn() {
        return startColumn;
    }
}
