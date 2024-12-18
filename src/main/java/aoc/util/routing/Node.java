package aoc.util.routing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Node {


    private long bestDistanceToOrigin = Long.MAX_VALUE;
    private List<Edge> outgoing = new ArrayList<>();
    private List<Edge> incoming = new ArrayList<>();
    protected String name;

    protected Node() {
    }

    public Node(String name) {
        this.name = name;
    }

    public void addEdge(Edge ed) {
        if (ed.getFrom() == this) {
            outgoing.add(ed);
        } else if (ed.getTo() == this) {
            incoming.add(ed);
        } else {
            throw new RuntimeException("Invalid edge");
        }
    }

    public List<Edge> getIncomingEdges() {
        return incoming;
    }

    public List<Edge> getOutgoingEdges() {
        return outgoing;
    }

    public long getBestDistanceToOrigin() {
        return bestDistanceToOrigin;
    }

    public boolean updateBestDistance() {
        boolean changed = false;
        for (Edge e: getIncomingEdges()) {
            long updated = e.getFrom().getBestDistanceToOrigin() + e.getWeight();
            if (updated < getBestDistanceToOrigin()) {
                changed = true;
                bestDistanceToOrigin = updated;
            }
        }
        return changed;
    }

    public List<Node> getNeighbours() {
        Set<Node> result = new HashSet<>();
        outgoing.stream().map(Edge::getTo).forEach(result::add);
        incoming.stream().map(Edge::getFrom).forEach(result::add);
        return new ArrayList<>(result);
    }

    public void setBestDistanceToOrigin(long bestDistanceToOrigin) {
        this.bestDistanceToOrigin = bestDistanceToOrigin;
    }

    public void removeEdge(Edge edgeToRemove) {
        incoming.remove(edgeToRemove);
        outgoing.remove(edgeToRemove);
    }

    public void removeAllConnectionsWith(Node other) {
        incoming.removeAll(incoming.stream().filter(e -> e.getFrom() == other).toList());
        outgoing.removeAll(outgoing.stream().filter(e -> e.getTo() == other).toList());
    }

    public void reset() {
        bestDistanceToOrigin = Long.MAX_VALUE;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public boolean isReachable() {
        return bestDistanceToOrigin != Long.MAX_VALUE;
    }
}
