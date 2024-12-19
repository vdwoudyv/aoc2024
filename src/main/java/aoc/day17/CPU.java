package aoc.day17;

import java.math.BigInteger;

public class CPU {

    private InputReader reader;
    private OutputLogger logger;
    private Register register;
    public static final BigInteger THREE = new BigInteger("3");
    public static final BigInteger EIGHT = new BigInteger("8");

    public CPU(InputReader reader, OutputLogger logger, Register register) {
        this.reader = reader;
        this.logger = logger;
        this.register = register;
    }

    public void run() {
        try {
            while (true) {
                execute(reader.read());
            }
        } catch (NoInputException nie) {
            // done!
        }
    }

    public void runOnce() {
        try {
            execute(reader.read());
            while (!reader.atBegin()) {
                execute(reader.read());
            }
        } catch(NoInputException nie) {
            // done
        }
    }

    public void execute(Integer operation) throws NoInputException {
        switch (operation) {
            case 0:
                adv();
                break;
            case 1:
                bxl();
                break;
            case 2:
                bst();
                break;
            case 3:
                jnz();
                break;
            case 4:
                bxc();
                break;
            case 5:
                out();
                break;
            case 6:
                bdv();
                break;
            case 7:
                cdv();
                break;
            default:
                throw new IllegalArgumentException("Unknown operation");
        }
    }

    private void adv() throws NoInputException {
        BigInteger numerator = register.getA();
        BigInteger divisor = BigInteger.TWO.pow(readComboOperand().intValue());
        register.setA(numerator.divide(divisor));
    }

    private void bxl() throws NoInputException {
        register.setB(new BigInteger("" + readLiteral()).xor(register.getB()));
    }

    private void bst() throws NoInputException {
        register.setB(readComboOperand().mod(EIGHT));
    }

    private void jnz() throws NoInputException {
        if (! register.getA().equals(BigInteger.ZERO)) {
            reader.setInstructionPointer(readLiteral());
        }
    }

    private void bxc() throws NoInputException {
        readLiteral();
        register.setB(register.getB().xor(register.getC()));
    }

    private void out() throws NoInputException {
        logger.log(readComboOperand().mod(EIGHT));
    }

    private void bdv() throws NoInputException {
        BigInteger numerator = register.getA();
        BigInteger divisor = BigInteger.TWO.pow(readComboOperand().intValue());
        register.setB(numerator.divide(divisor));
    }

    private void cdv() throws NoInputException {
        BigInteger numerator = register.getA();
        BigInteger divisor = BigInteger.TWO.pow(readComboOperand().intValue());
        register.setC(numerator.divide(divisor));
    }

    private Integer readLiteral() throws NoInputException {
        return reader.read();
    }

    private BigInteger readComboOperand() throws NoInputException {
        int literal = reader.read();
        return switch (literal) {
            case 0 -> BigInteger.ZERO;
            case 1 -> BigInteger.ONE;
            case 2 -> BigInteger.TWO;
            case 3 -> THREE;
            case 4 -> register.getA();
            case 5 -> register.getB();
            case 6 -> register.getC();
            default -> throw new IllegalArgumentException("Invalid combo operand");
        };
    }
}
