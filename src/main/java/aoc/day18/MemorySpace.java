package aoc.day18;

import aoc.day16.MazeNode;
import aoc.util.Coordinate;
import aoc.util.Direction;
import aoc.util.routing.Edge;
import aoc.util.routing.Node;
import aoc.util.routing.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemorySpace {

    private Map<Coordinate, Node> nodeMap = new HashMap<>();
    private List<Coordinate> corruptionList;
    private int width;
    private int height;
    private World world;

    public MemorySpace(List<Coordinate> corruptionList, int width, int height) {
        this.width = width;
        this.height = height;
        this.corruptionList = new ArrayList<>(corruptionList);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Coordinate current = new Coordinate(i, j);
                Node n = new Node(current.toString());
                nodeMap.put(current, n);
            }
        }
        for (Coordinate c: nodeMap.keySet()) {
            Node n = nodeMap.get(c);
            for (Direction d: Direction.values()) {
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

    public Coordinate corrupt() {
        Coordinate corrupt = corruptionList.remove(0);
        Node toRemove = nodeMap.get(corrupt);
        world.removeNode(toRemove);
        nodeMap.remove(corrupt);
        return corrupt;
    }

    public int nbSteps() {
        Node start = nodeMap.get(new Coordinate(0,0));
        Node end = nodeMap.get(new Coordinate(width-1, height-1));
        world.reset();
        return world.getRouteDs(start, end).size();
    }


    public String toString() {
        StringBuilder sb = new StringBuilder("\n");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (nodeMap.containsKey(new Coordinate(j, i))) {
                    sb.append(".");
                } else {
                    sb.append("#");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
