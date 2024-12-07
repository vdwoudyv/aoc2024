package aoc.util;

public record DoubleCoordinate(double x, double y) {

    public static final double EPSILON = 0.0000001;

    public DoubleCoordinate translate(double diffX, double diffY) {
        return new DoubleCoordinate(x + diffX, y + diffY);
    }


    public boolean isInBounds(double minX, double maxX, double minY, double maxY) {
        if (x < minX-EPSILON || x > maxX+EPSILON || y < minY-EPSILON || y > maxY+EPSILON) {
            return false;
        } else {
            return true;
        }
    }
}
