package aoc.day17;

import java.math.BigInteger;
import java.util.List;

public class Register {

    private BigInteger a;
    private BigInteger b;
    private BigInteger c;

    public Register(BigInteger a, BigInteger b, BigInteger c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Register(List<String> strings) {
        a = new BigInteger(strings.get(0).substring(11).trim());
        b = new BigInteger(strings.get(1).substring(11).trim());
        c = new BigInteger(strings.get(2).substring(11).trim());
    }

    public BigInteger getA() {
        return a;
    }

    public void setA(BigInteger a) {
        this.a = a;
    }

    public BigInteger getB() {
        return b;
    }

    public void setB(BigInteger b) {
        this.b = b;
    }

    public BigInteger getC() {
        return c;
    }

    public void setC(BigInteger c) {
        this.c = c;
    }

    public void resetTo(BigInteger a, BigInteger b, BigInteger c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}
