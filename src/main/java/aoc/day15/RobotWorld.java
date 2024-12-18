package aoc.day15;

import aoc.util.CellWorld;
import aoc.util.Coordinate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RobotWorld extends CellWorld<Item> {

    public Item createItem(String s, Coordinate coordinate) {
        return switch (s) {
            case "#" -> new Wall(coordinate, this);
            case "@" -> new Robot(coordinate, this);
            case "O" -> new Box(coordinate, this);
            case "." -> null;
            default -> throw new IllegalArgumentException("Unknown item: " + s);
        };
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Item previous = null;
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j< getWidth(); j++) {
                Item item = getCell(new Coordinate(j, i));
                if (item == null) {
                    sb.append(".");
                } else {
                    if (item != previous) {
                        sb.append(item);
                    }
                }
                previous = item;
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void excuteMoves(String input) {
        Robot robot = getCells().stream().filter(s -> s instanceof Robot).map(s-> (Robot) s).findFirst().orElse(null);

        String[] directions = input.split("");
        for (String direction: directions) {
            robot.move(direction);
        }
    }

    public void updatePosition(Item itemToMove, Coordinate c) {
        if (itemToMove instanceof Box && ((Box) itemToMove).getWidth()>1) {
            updateMultiplePositions((Box) itemToMove, c);
        } else {
            if (getCell(c) == null) {
                cellMap.remove(itemToMove.getCoordinate());
                cellMap.put(c, itemToMove);
                itemToMove.setCoordinate(c);
            } else {
                throw new IllegalArgumentException("Already occupied");
            }
        }
    }

    private void updateMultiplePositions(Box itemToMove, Coordinate c) {
        List<Coordinate> targetCoordinates = new ArrayList<>();
        for (int i = 0; i< itemToMove.getWidth();i++) {
            targetCoordinates.add(new Coordinate(c.x()+i, c.y()));
        }
        List<Coordinate> sourceCoordinates = itemToMove.getAllCoordinates();

        if (targetCoordinates.stream().filter(coord -> !sourceCoordinates.contains(coord)).allMatch(cell -> getCell(cell) == null)) {
            if (sourceCoordinates.size() != targetCoordinates.size()) {
                throw new IllegalArgumentException("Unexpected situation");
            }
            for (int i = 0; i < sourceCoordinates.size();i++) {
                Coordinate current = sourceCoordinates.get(i);
                Coordinate target = targetCoordinates.get(i);
                if (! targetCoordinates.contains(current)) {
                    cellMap.remove(current);
                }
                if (!sourceCoordinates.contains(target)) {
                    cellMap.put(target, itemToMove);
                }
                if (i == 0) {
                    itemToMove.setCoordinate(target);
                }
            }
        } else {
            throw new IllegalArgumentException("One of the targetcells is already occupied.");
        }
    }

    public long getSumGPS() {
        return getCells().stream().filter(c-> c instanceof Box).mapToLong(c -> ((Box) c).getGps()).sum();
    }

    public void widen() {
        Map<Coordinate, Item> newCellMap = new HashMap<>();
        for (Map.Entry<Coordinate, Item> element: cellMap.entrySet()) {
            Coordinate currentCoord = element.getKey();
            Coordinate firstNew = new Coordinate(currentCoord.x()*2, currentCoord.y());
            Coordinate secondNew = new Coordinate(currentCoord.x()*2+1, currentCoord.y());
            if (element.getValue() instanceof Wall) {
                Wall first = new Wall(firstNew, this);
                newCellMap.put(firstNew, first);
                Wall second = new Wall(secondNew, this);
                newCellMap.put(secondNew, second);
            } else if (element.getValue() instanceof Box) {
                Box newBox = new Box(firstNew, this, 2);
                newCellMap.put(firstNew, newBox);
                newCellMap.put(secondNew, newBox);
            } else if (element.getValue() instanceof Robot) {
                Robot robot = new Robot(firstNew, this);
                newCellMap.put(firstNew, robot);
            }
        }
        cellMap.clear();
        cellMap.putAll(newCellMap);
    }
}
