package aoc.util;

public record Coordinate3D(long x, long y, long z) {

    public Coordinate3D translate(long diffX, long diffY, long diffZ) {
        return new Coordinate3D(x + diffX, y + diffY, z + diffZ);
    }

    public long getManhattan(Coordinate3D other) {
        return Math.abs(this.x() - other.x()) + Math.abs(this.y() - other.y())  + Math.abs(this.z() - other.z());
    }

    public Coordinate3D subtract(Coordinate3D other) {
        return new Coordinate3D(this.x() - other.x(), this.y() - other.y(), this.z() - other.z());
    }

    public Coordinate3D add(Coordinate3D other) {
        return new Coordinate3D(this.x() + other.x(), this.y() + other.y(), this.z() + other.z());
    }

    /**
     * If both coordinates represent a vector, this method returns the cross product of the two vectors (this
     * is a vector perpendicular to both of the vectors).
     */
    public Coordinate3D crossProduct(Coordinate3D other) {
        return new Coordinate3D(this.y() * other.z() - this.z() * other.y(),
                this.z() * other.x() - this.x() * other.z(),
                this.x() * other.y() - this.y() * other.x());
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + ", " + z +"]";
    }
}
