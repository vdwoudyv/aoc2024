package aoc.day8;

import aoc.util.Coordinate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class City {

    List<Antenna> antennas = new ArrayList<>();
    private int width;
    private int height;

    public City(List<List<String>> input) {
        width = input.size();
        height = input.get(0).size();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                String symbol = input.get(i).get(j);
                if (!symbol.equals(".")) {
                    antennas.add(new Antenna(new Coordinate(i, j), symbol));
                }
            }
        }
    }

    public List<Coordinate> findAntinodes(int maxDist) {
        List<Coordinate> result = new ArrayList<>();
        for (int i = 0; i < antennas.size(); i++) {
            for (int j = i+1; j < antennas.size(); j++) {
                if (antennas.get(i).symbol().equals(antennas.get(j).symbol())) {
                    result.addAll(getAntinodes(antennas.get(i).coordinate(), antennas.get(j).coordinate(), maxDist));
                }
            }
        }
        // remove duplicates
        return new HashSet<>(result).stream().toList();
    }

    private List<Coordinate> getAntinodes(Coordinate first, Coordinate second, int maxDist) {
        List<Coordinate> result = new ArrayList<>();

        int diffX = Math.abs(first.x() - second.x());
        int diffY = Math.abs(first.y() - second.y());

        int distance = 1;
        Coordinate firstAntinode = new Coordinate(
                first.x() < second.x() ? first.x() - (diffX*distance) : first.x() + (diffX * distance),
                first.y() < second.y() ? first.y() - (diffY*distance) : first.y() + (diffY * distance));

        while (inBounds(firstAntinode) && distance <= maxDist) {
            result.add(firstAntinode);
            distance++;
            firstAntinode = new Coordinate(
                    first.x() < second.x() ? first.x() - (diffX*distance) : first.x() + (diffX * distance),
                    first.y() < second.y() ? first.y() - (diffY*distance) : first.y() + (diffY * distance));
        }

        distance = 1;
        Coordinate secondAntinode = new Coordinate(
                second.x() > first.x() ? second.x() + (diffX*distance) : second.x() - (diffX*distance),
                second.y() > first.y() ? second.y() + (diffY*distance) : second.y() - (diffY*distance));

        while (inBounds(secondAntinode) && distance <= maxDist) {
            result.add(secondAntinode);
            distance++;
            secondAntinode = new Coordinate(
                    second.x() > first.x() ? second.x() + (diffX*distance) : second.x() - (diffX*distance),
                    second.y() > first.y() ? second.y() + (diffY*distance) : second.y() - (diffY*distance));
        }
        if (maxDist > 1) {
            result.add(first);
            result.add(second);
        }
        return result;
    }

    private boolean inBounds(Coordinate coord) {
        return coord.x() >=0 && coord.x() < width && coord.y() >=0 && coord.y() < height;
    }
}
