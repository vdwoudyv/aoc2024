package aoc.day9;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AocFileSystem {

    private final List<Cell> cells = new ArrayList<>();


    public AocFileSystem(String input) {
        String[] parts = input.split("");
        for (int i = 0; i<  parts.length;i++) {
            int length = Integer.parseInt(parts[i]);
            if (i % 2 == 1) {
                cells.add(new Space(length));
            } else {
                cells.add(new NumberCell(i/2, length));
            }
        }
    }

    public boolean singleCompactStep() {
        // Remove any free space at end of cells.
        while (cells.get(cells.size()-1) instanceof Space) {
            cells.remove(cells.size()-1);
        }
        NumberCell last = (NumberCell) cells.get(cells.size()-1);
        Optional<Space> firstSpace = getFirstSpace();
        if (firstSpace.isPresent()) {
            Space space = firstSpace.get();
            if (space.getLength() >= last.getLength()) {
                space.reduceLength(last.getLength());
                swap(space, last, space);
                cells.remove(cells.size()-1);
            } else {
                NumberCell toReplace = new NumberCell(last.getValue(), space.getLength());
                last.reduceLength(space.getLength());
                swap(space, toReplace);
            }
            if (last.getLength() == 0) {
                cells.remove(cells.size()-1);
            }
            while (cells.get(cells.size()-1) instanceof Space) {
                cells.remove(cells.size()-1);
            }
            return true;
        } else {
            return false;
        }
    }

    public void compact() {
        boolean changed = true;
        while (changed) {
            changed = singleCompactStep();
        }
    }


    public void compactFullFiles() {
        for (int i = cells.size()-1; i>=0; i--) {
            if (cells.get(i) instanceof NumberCell numberCell) {
                moveFullCell(numberCell, i);
            }
        }
    }

    public boolean moveFullCell(NumberCell cell, int maxSpaceIndex) {
        int originalIndex = cells.indexOf(cell);
        for (int i =0; i<maxSpaceIndex; i++) {
            if (cells.get(i) instanceof Space space) {
                if (space.getLength()>= cell.getLength()) {
                    space.reduceLength(cell.getLength());
                    if (space.getLength() >0) {
                        swap(space, cell, space);
                        cells.remove(originalIndex+1);
                        cells.add(originalIndex+1, new Space(cell.getLength()));
                    } else {
                        swap(space, cell);
                        cells.remove(originalIndex);
                        cells.add(originalIndex, new Space(cell.getLength()));
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public long checksum() {
        int index = 0;
        long total = 0L;
        for (Cell c: cells) {
            total += c.checksum(index);
            index += c.getLength();
        }
        return total;
    }

    private Optional<Space> getFirstSpace() {
        return cells.stream().filter(c -> c instanceof Space).map(c -> (Space) c).findFirst();
    }

    public String toString() {
        return cells.stream().map(Object::toString).collect(Collectors.joining());
    }


    /**
     * Replaces a cell by another cell
     */
    public void swap(Cell in, Cell out) {
        int index = cells.indexOf(in);
        cells.remove(index);
        cells.add(index, out);
    }

    /**
     * Replaces given cell by the sequence of two cells.
     */
    public void swap(Cell in, Cell first, Cell second) {
        int index = cells.indexOf(in);
        cells.remove(index);
        cells.add(index, second);
        cells.add(index, first);
    }
}
