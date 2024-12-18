package aoc.day17;

import java.util.List;

public class Register {

    private int a;
    private int b;
    private int c;

    public Register(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Register(List<String> strings) {
        a = Integer.parseInt(strings.get(0).substring(11).trim());
        b = Integer.parseInt(strings.get(1).substring(11).trim());
        c = Integer.parseInt(strings.get(2).substring(11).trim());
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public void resetTo(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}
