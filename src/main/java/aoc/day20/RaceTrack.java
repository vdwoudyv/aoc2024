package aoc.day20;

import aoc.util.Coordinate;
import aoc.util.Direction;
import aoc.util.routing.Edge;
import aoc.util.routing.Node;
import aoc.util.routing.World;

import java.util.*;

public class RaceTrack {

    private int width;
    private int height;
    private Coordinate start;
    private Coordinate end;
    private World<TrackNode> world;
    private Map<Coordinate, TrackNode> nodeMap = new HashMap<>();


    public RaceTrack(List<String> input) {
        Set<Coordinate> actualCoordinates = extractMazeCoordinates(input);
        for (Coordinate c : actualCoordinates) {
            nodeMap.put(c, new TrackNode(c));
        }
        for (Coordinate c : actualCoordinates) {
            Node n = nodeMap.get(c);
            for (Direction d : Direction.values()) {
                Node neighbour = nodeMap.get(c.getAdjacent(d));
                if (neighbour != null) {
                    Edge e = new Edge(1, n, neighbour);
                    n.addEdge(e);
                    neighbour.addEdge(e);
                }
            }
        }
        world = new World<>(new ArrayList<>(nodeMap.values()));
    }

    public List<Coordinate> getShortestPath() {
        List<Edge> path = world.getRouteDs(nodeMap.get(start), nodeMap.get(end));
        List<Coordinate> result = new ArrayList<>();
        result.add(((TrackNode) path.get(0).getFrom()).getCoordinate());
        for (Edge e: path) {
            result.add(((TrackNode) e.getTo()).getCoordinate());
        }
        return result;
    }

    public List<Cheat> getCheats(int picoSeconds) {
        List<Cheat> result = new ArrayList<>();
        List<Coordinate> path = getShortestPath();
        for (int i = 0; i < path.size(); i++) {
            for (int j = i; j < path.size(); j++) {
                Coordinate first = path.get(i);
                Coordinate second = path.get(j);
                if (Cheat.cheatPossible(first, second, picoSeconds)) {
                    Cheat potentialCheat = new Cheat(first, second, j-i);
                        result.add(potentialCheat);
                }
            }
        }
        return result;
    }

    private Set<Coordinate> extractMazeCoordinates(List<String> input) {
        height = input.size();
        width = input.get(0).length();

        Set<Coordinate> actualCoordinates = new HashSet<>();
        for (int row = 0; row < input.size(); row++) {
            String[] parts = input.get(row).split("");
            for (int column = 0; column < parts.length; column++) {
                String value = parts[column];
                if (value.equals(".") || value.equals("S") || value.equals("E")) {
                    Coordinate c = new Coordinate(column, row);
                    actualCoordinates.add(c);
                    if (value.equals("S")) {
                        start = c;
                    } else if (value.equals("E")) {
                        end = c;
                    }
                }
            }
        }
        return actualCoordinates;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder("\n");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                boolean present = nodeMap.containsKey(new Coordinate(j, i));
                sb.append(present ? "." : "#");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
