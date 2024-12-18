package aoc.util.routing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Path {

    private List<Edge> edgeList = new ArrayList<>();
    private List<Path> continuations = new ArrayList<>();
    private Node startNode;

    public Path(Edge edge, Node start) {
        this.edgeList.add(edge);
        this.startNode = start;
    }

    public Path(Edge edge, Path continuation, Node start) {
        edgeList.add(edge);
        continuations.add(continuation);
        this.startNode = start;
    }

    public Path(List<Path> continuations, Node start) {
        this.continuations.addAll(continuations);
        this.startNode = start;
    }

    public List<List<Edge>> separate(Map<Path, List<List<Edge>>> cache) {
        if (continuations.isEmpty()) {
            List<List<Edge>> result = new ArrayList<>();
            result.add(edgeList);
            return result;
        } else {
            List<List<Edge>> result = new ArrayList<>();
            for (Path next: continuations) {
                List<List<Edge>> nextExpansion;
                if (cache.containsKey(next)) {
                    nextExpansion = cache.get(next);
                } else {
                    nextExpansion = next.separate(cache);
                    cache.put(next, nextExpansion);
                }
                for (List<Edge> path: nextExpansion) {
                    List<Edge> full = new ArrayList<>(edgeList);
                    full.addAll(path);
                    result.add(full);
                }
            }
            return result;
        }
    }

    public Path prepend(List<Edge> toPrepend) {
        this.edgeList.addAll(0, toPrepend);
        return this;
    }

    public List<Path> expand() {
        List<Edge> directIncoming = getBegin().getIncomingEdges().stream()
                .filter(e -> e.getFrom().getBestDistanceToOrigin() + e.getWeight() == e.getTo().getBestDistanceToOrigin())
                .toList();
        if (directIncoming.isEmpty()) {
            throw new RuntimeException("Unknown edge");
        } else if (directIncoming.size() == 1) {
            edgeList.add(0, directIncoming.get(0));
            List<Path> justUs = new ArrayList<>();
            justUs.add(this);
            return justUs;
        } else {
            List<Path> paths =  new ArrayList<>();
            for (Edge edge: directIncoming) {
                paths.add(new Path(edge, this, startNode));
            }
            return paths;
        }
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }

    public Node getBegin() {
        if (edgeList.isEmpty()) {
            if (continuations.isEmpty()) {
                throw new RuntimeException("Incorrect path");
            } else {
                return continuations.get(0).getBegin();
            }
        } else {
            return edgeList.get(0).getFrom();
        }
    }

    public boolean isComplete() {
        return getBegin().equals(startNode);
    }

    public List<Path> getContinuations() {
        return continuations;
    }
}
