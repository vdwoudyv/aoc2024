package aoc.day13;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

public class Equation {

    // Basically we have two equations: a11*a + a12*b = a13 and a21*a + a22*b = a23

    private final BigDecimal a11;
    private final BigDecimal a12;
    private final BigDecimal a13;

    private final BigDecimal a21;
    private final BigDecimal a22;
    private final BigDecimal a23;

    public Equation(List<String> input, BigDecimal initialIncrement) {
        a11 = new BigDecimal(input.get(0).substring(input.get(0).indexOf("+")+1, input.get(0).indexOf(","))).setScale(12, RoundingMode.HALF_UP);
        a21 = new BigDecimal(input.get(0).substring(input.get(0).lastIndexOf("+")+1)).setScale(12, RoundingMode.HALF_UP);

        a12 = new BigDecimal(input.get(1).substring(input.get(1).indexOf("+")+1, input.get(1).indexOf(","))).setScale(12, RoundingMode.HALF_UP);
        a22 = new BigDecimal(input.get(1).substring(input.get(1).lastIndexOf("+")+1)).setScale(12, RoundingMode.HALF_UP);

        a13 = new BigDecimal(input.get(2).substring(input.get(2).indexOf("=")+1, input.get(2).indexOf(","))).setScale(12, RoundingMode.HALF_UP).add(initialIncrement);
        a23 = new BigDecimal(input.get(2).substring(input.get(2).lastIndexOf("=")+1)).setScale(12, RoundingMode.HALF_UP).add(initialIncrement);
    }

    private BigDecimal getB() {
        return a21.multiply(a13).subtract(a11.multiply(a23)).divide(a12.multiply(a21).subtract(a11.multiply(a22)), RoundingMode.HALF_UP);
    }

    private BigDecimal getA() {
        return a13.subtract(a12.multiply(getB())).divide(a11, RoundingMode.HALF_UP);
    }

    public boolean hasSolution(Integer max) {
        try {
            long a = getA().longValueExact();
            long b = getB().longValueExact();
            return a > 0 && b > 0 && (max == null || (a <= max && b <= max));
        } catch (ArithmeticException ae) {
            // either a or b has a decimal part
            return false;
        }
    }

    public long getCost() {
        return getA().longValueExact()*3+getB().longValueExact();
    }

}
