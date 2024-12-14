package aoc.day14;

import aoc.util.Coordinate;

public class Robot implements Comparable<Robot> {

    private Coordinate location;
    private Coordinate velocity;
    private RobotWorld world;

    public Robot(String input, RobotWorld world) {
        String[] loc = input.substring(2, input.indexOf(" ")).split(",");
        String[] vel = input.substring(input.lastIndexOf("=") + 1).split(",");
        location = new Coordinate(Integer.parseInt(loc[0]), Integer.parseInt(loc[1]));
        velocity = new Coordinate(Integer.parseInt(vel[0]), Integer.parseInt(vel[1]));
        this.world = world;
    }


    public Robot move(int nbOfSeconds) {
        int x = (location.x() + velocity.x() * nbOfSeconds) % world.getWidth();
        int y = (location.y() + velocity.y() * nbOfSeconds) % world.getHeight();

        if (x < 0) {
            x = x + world.getWidth();
        }
        if (y < 0) {
            y = y + world.getHeight();
        }
        location = new Coordinate(x, y);
        return this;
    }

    public Coordinate getLocation() {
        return location;
    }

    public int getQuadrant() {
        int midX = world.getWidth() / 2;
        int midY = world.getHeight() / 2;
        if (location.x() == midX || location.y() == midY) {
            return 5;
        } else if (location.x() < midX) {
            if (location.y() < midY) {
                return 1;
            } else {
                return 3;
            }
        } else {
            if (location.y() < midY) {
                return 2;
            } else {
                return 4;
            }
        }
    }

    public Coordinate getVelocity() {
        return velocity;
    }

    @Override
    public int compareTo(Robot o) {
        return location.compareTo(o.location);
    }
}
