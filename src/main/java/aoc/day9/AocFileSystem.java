package aoc.day9;

import aoc.util.Tuple;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AocFileSystem {

    private List<Cell> cells;

    public AocFileSystem(List<Integer> data) {
        cells = new ArrayList<>();
        int runningIndex = 0;
        int nbNumbers = 0;
        for (int i =0; i < data.size(); i++) {
            if (i%2 == 0) {
                // Number
                if (data.get(i) != 0) {
                    cells.add(new Cell(nbNumbers, data.get(i), true, runningIndex));
                }
                nbNumbers++;
            } else {
                if (data.get(i) != 0) {
                    cells.add(new Cell(-1, data.get(i), false, runningIndex));
                }
            }
            runningIndex = runningIndex + data.get(i);
        }
    }

    public void compactFragmenting() {
        boolean changed;
        do {
            changed = compactStep();
        } while (changed);
    }

    public void compactWholeBlocks() {
        cleanup();
        reindex();
        List<Cell> toAttemptCompact = new ArrayList<>(cells.stream().filter(Cell::isNumber).toList());
        for (int i = toAttemptCompact.size() -1; i >=0; i--) {
            compactBlock(toAttemptCompact.get(i));
            reindex();
          }
    }


    public void cleanup() {
        int index = 0;
        while (index < cells.size()) {
            Cell current = cells.get(index);
            if (!current.isNumber() && current.getLength() == 0) {
                cells.remove(index);
            } else {
                if (index < cells.size() - 1) {
                    Cell next = cells.get(index+1);
                    if ((!current.isNumber()) && (!next.isNumber())) {
                        next.increaseLength(current);
                        cells.remove(index);
                    } else {
                        index++;
                    }
                } else {
                    index++;
                }
            }
        }
    }

    private void compactBlock(Cell cell) {
        int blockToCompact = cells.indexOf(cell);
        int firstSpace = findFirstSpace(cell.getLength());
        if (firstSpace != -1 && firstSpace < cell.getBeginIndex()) {
            Cell space = cells.get(firstSpace);
            cells.add(firstSpace, cell);
            space.reduceLength(cell.getLength());
            replaceWithSpace(cells, blockToCompact+1);
            if (!cells.get(cells.size() - 1).isNumber()) {
                cells.remove(cells.size()-1);
            }
        }
    }

    private void replaceWithSpace(List<Cell> cells, int blockToCompact) {
        Cell toReplace = cells.get(blockToCompact);
        Cell space = new Cell(-1, toReplace.getLength(), false, toReplace.getBeginIndex());
        cells.remove(blockToCompact);
        if (blockToCompact < cells.size()) { // no use adding space at the end.
            cells.add(blockToCompact, space);
        }
    }


    public long computeHash() {
        reindex();
        return cells.stream().mapToLong(Cell::checksum).sum();
    }

    private void reindex() {
        int runningIndex = 0;
        for (Cell c: cells) {
            c.setBeginIndex(runningIndex);
            runningIndex += c.getLength();
        }
    }

    // true if something could be compacted, false otherwise.
    public boolean compactStep() {
        Cell lastCell = cells.get(cells.size()-1);
        if (! lastCell.isNumber()) {
            cells.remove(cells.size()-1);
            return true;
        } else {
            int index = findFirstSpace(0);
            if (index == -1) {
                return false;
            }
            else {
                Cell space = cells.get(index);
                if (space.getLength() >= lastCell.getLength()) {
                    cells.add(index, lastCell);
                    cells.remove(cells.size()-1);
                    space.reduceLength(lastCell.getLength());
                } else {
                    space.fill(lastCell.getValue());
                    lastCell.reduceLength(space.getLength());
                    if (space.getLength() == 0) {
                        cells.remove(space);
                    }
                }
                return true;
            }
        }
    }

    public int findFirstSpace(int minSize) {
        for (int i = 0; i < cells.size(); i++) {
            if ((!cells.get(i).isNumber()) && (cells.get(i).getLength() >= minSize)) {
                return i;
            }
        }
        return -1;
    }

    public String toString() {
        return cells.stream().map(Cell::toString).collect(Collectors.joining(""));
    }
}
