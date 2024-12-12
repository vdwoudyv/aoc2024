package aoc.util;

@FunctionalInterface
public interface CellCreator<T extends Cell> {

    T create(String content, Coordinate coordinate, CellWorld<T> w);

}
