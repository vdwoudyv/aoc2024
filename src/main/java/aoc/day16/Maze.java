package aoc.day16;

import aoc.util.Coordinate;
import aoc.util.Direction;
import aoc.util.routing.Edge;
import aoc.util.routing.Path;
import aoc.util.routing.World;

import java.util.*;

public class Maze {

    private int width;
    private int height;
    private Coordinate start;
    private Coordinate end;
    private World<MazeNode> world;
    private Map<Coordinate, List<MazeNode>> nodeMap;


    public Maze(List<String> input) {
        Set<Coordinate> actualCoordinates = extractMazeCoordinates(input);

        nodeMap = constructNodeMap(actualCoordinates);
        List<MazeNode> allNodes = new ArrayList<>(nodeMap.values().stream().flatMap(Collection::stream).toList());

        for (MazeNode mazeNode: allNodes) {
            addOutgoingEdges(mazeNode, nodeMap);
        }
        world = new World<>(allNodes);
    }

    public long getBestPathScore() {
        MazeNode startNode = getStartNode();
        long best = -1;
        for (MazeNode endNode: nodeMap.get(end)) {
            List<Edge> path = world.getRouteDs(startNode, endNode);
            long cost = path.stream().mapToLong(Edge::getWeight).sum();
            best = (best == -1) ? cost : Math.min(best, cost);
        }
        return best;
    }


    public long getNbOfBestTiles() {
        MazeNode startNode = getStartNode();
        Set<Coordinate> coordinates = new HashSet<>();
        MazeNode bestEnd = getShortestEnd(startNode);
        List<Path> allBestPaths = world.getAllShortestRouteDs(startNode, bestEnd);
        Set<Path> toDo = new HashSet<>(allBestPaths);
        Set<Coordinate> done = new HashSet<>();
        while (! toDo.isEmpty()) {
            Path p = toDo.stream().findAny().get();
            for (Edge e: p.getEdgeList()) {
                done.add(((MazeNode)e.getFrom()).getCoordinate());
                done.add(((MazeNode)e.getTo()).getCoordinate());
            }
            toDo.remove(p);
            toDo.addAll(p.getContinuations());
        }
        return done.size();
    }

    private MazeNode getStartNode() {
        MazeNode startNode = nodeMap.get(start).stream().filter(n -> ! n.isVertical()).findFirst().orElse(null);
        return startNode;
    }

    // there are multiple nodes marked as end (horizontal/vertical).
    // this returns the one with the shortest path from start to finish
    private MazeNode getShortestEnd(MazeNode startNode) {
        long shortest = -1;
        MazeNode bestEnd = null;
        for (MazeNode endNode: nodeMap.get(end)) {
            long score = world.getRouteDs(startNode, endNode).stream().mapToLong(Edge::getWeight).sum();
            shortest = (shortest == -1) ? score : Math.min(score, shortest);
            if (score == shortest) {
                bestEnd = endNode;
            }
        }
        return bestEnd;
    }

    private void addOutgoingEdges(MazeNode currentNode, Map<Coordinate, List<MazeNode>> nodes) {
        for (Direction d: Direction.values()) {
            List<MazeNode> neighbours = nodes.getOrDefault(currentNode.getCoordinate().getAdjacent(d), new ArrayList<>());
            for (MazeNode other: neighbours) {
                if (currentNode.isVertical() == other.isVertical()) {
                    Edge e = new Edge(1, currentNode, other);
                    currentNode.addEdge(e);
                    other.addEdge(e);
                } else {
                    Edge e = new Edge(1001, currentNode, other);
                    currentNode.addEdge(e); // a bit cheating.... this allows you to go back immediately for 1 (which actually requires you to turn twice)
                    other.addEdge(e);
                }
            }
        }
    }

    private Map<Coordinate, List<MazeNode>> constructNodeMap(Set<Coordinate> actualCoordinates) {
        Map<Coordinate, List<MazeNode>> result = new HashMap<>();
        for (Coordinate coordinate: actualCoordinates) {
            List<MazeNode> nodesOnLocation = new ArrayList<>();
            if (actualCoordinates.contains(coordinate.getAdjacent(Direction.NORTH))
                    || actualCoordinates.contains(coordinate.getAdjacent(Direction.SOUTH))
            || coordinate.equals(start) || coordinate.equals(end)) {
                String name = coordinate + "V";
                nodesOnLocation.add(new MazeNode(name, coordinate, this, true));
            }
            if (actualCoordinates.contains(coordinate.getAdjacent(Direction.EAST)) || actualCoordinates.contains(coordinate.getAdjacent(Direction.WEST))
                    || coordinate.equals(start) || coordinate.equals(end)) {
                String name = coordinate + "H";
                nodesOnLocation.add(new MazeNode(name, coordinate, this, false));
            }
            result.put(coordinate, nodesOnLocation);
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
        List<MazeNode> empty = new ArrayList<>();
        String[] tokens = new String[] { "#", ".", "+"};
        StringBuilder sb = new StringBuilder("\n");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int nb = nodeMap.getOrDefault(new Coordinate(j, i), empty).size();
                sb.append(tokens[nb]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
