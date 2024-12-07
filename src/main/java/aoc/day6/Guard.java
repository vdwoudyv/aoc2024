package aoc.day6;

import aoc.util.Direction;

public class Guard {

    private int row;
    private int column;
    private Direction direction;
    private String[][] area;
    private Room room;

    public Guard(Room r) {
        room = r;
        reset();
    }


    public boolean walk() {
        reset();
        while (inArea(row, column)) {
            if (hasPassed(area[row][column], direction.getSymbol())) {
                return true;
            }
            updateSymbol(row, column);
            int newRow = row + direction.getDiffY();
            int newColumn = column + direction.getDiffX();
            if (room.isObstacle(newRow, newColumn)) {
                direction = direction.turnRight();
            } else {
                this.row = newRow;
                this.column = newColumn;
            }
        }
        return false;
    }

    private void updateSymbol(int row, int column) {
        String currentSymbol = area[row][column];
        switch (currentSymbol) {
            case ">":
                if (direction == Direction.WEST) {
                    area[row][column] = "-";
                }
                break;
            case "<":
                if (direction == Direction.EAST) {
                    area[row][column] = "-";
                }
                break;
            case "v":
                if (direction == Direction.NORTH) {
                    area[row][column] = "|";
                }
                break;
            case "^":
                if (direction == Direction.SOUTH) {
                    area[row][column] = "|";
                }
                break;
            default:
                area[row][column] = direction.getSymbol();
        }
    }

    private boolean hasPassed(String areaSymbol, String currentDirectionSymbol) {
        if (areaSymbol.equals(currentDirectionSymbol)) {
            return true;
        } else if (areaSymbol.equals("|") &&
                (currentDirectionSymbol.equals(Direction.NORTH.getSymbol()) ||
                        currentDirectionSymbol.equals(Direction.SOUTH.getSymbol()))
        ) {
            return true;
        } else if (areaSymbol.equals("-") &&
                (currentDirectionSymbol.equals(Direction.EAST.getSymbol()) ||
                        currentDirectionSymbol.equals(Direction.WEST.getSymbol()))
        ) {
            return true;
        } else {
            return false;
        }
    }

    private void reset() {
        this.row = room.getStartRow();
        this.column = room.getStartColumn();
        direction = Direction.NORTH;
        this.area = new String[room.getWidth()][room.getHeight()];
        for (int i = 0; i < this.area.length; i++) {
            for (int j = 0; j < this.area[0].length; j++) {
                this.area[i][j] = ".";
            }
        }
    }

    public long nbAreas() {
        long count = 0;
        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area[0].length; j++) {
                if (!area[i][j].equals(".")) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean inArea(int row, int column) {
        int width = area.length;
        int height = area[0].length;
        return row < width && row >= 0 && column < height && column >= 0;
    }

    public String printWalk() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area[0].length; j++) {
                if (room.isObstacle(i, j)) {
                    sb.append("#");
                } else {
                    sb.append(area[i][j]);
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
