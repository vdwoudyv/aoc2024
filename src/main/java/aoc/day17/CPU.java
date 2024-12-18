package aoc.day17;

public class CPU {

    private InputReader reader;
    private OutputLogger logger;
    private Register register;

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

    public Integer runTilEquivalent() {
        int valueA = 2000000000;
        while (true) {
            register.resetTo(valueA, 0, 0);
            reader.reset();
            logger.reset();
            boolean stillOk = true;
            try {
                while (stillOk) {
                    stillOk = execute(reader.read());
                }
            } catch (NoInputException nie) {
                if (logger.valid()) {
                    return valueA;
                }
            } catch (Exception e) {
            }
            valueA++;
            if (valueA % 100000000 == 0) {
                System.out.print(".");
            }
        }
    }

    public boolean execute(Integer operation) throws NoInputException {
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
                return out();
            case 6:
                bdv();
                break;
            case 7:
                cdv();
                break;
            default:
                throw new IllegalArgumentException("Unknown operation");
        }
        return true;
    }

    private void adv() throws NoInputException {
        int numerator = register.getA();
        int divisor = (int) Math.pow(2, readComboOperand());
        register.setA(numerator / divisor);
    }

    private void bxl() throws NoInputException {
        register.setB(readLiteral() ^ register.getB());
    }

    private void bst() throws NoInputException {
        register.setB(readComboOperand() % 8);
    }

    private void jnz() throws NoInputException {
        if (register.getA() != 0) {
            reader.setInstructionPointer(readLiteral());
        }
    }

    private void bxc() throws NoInputException {
        readLiteral();
        register.setB(register.getB() ^ register.getC());
    }

    private boolean out() throws NoInputException {
        return logger.log(readComboOperand() % 8);
    }

    private void bdv() throws NoInputException {
        int numerator = register.getA();
        int divisor = (int) Math.pow(2, readComboOperand());
        register.setB(numerator / divisor);
    }

    private void cdv() throws NoInputException {
        int numerator = register.getA();
        int divisor = (int) Math.pow(2, readComboOperand());
        register.setC(numerator / divisor);
    }

    private Integer readLiteral() throws NoInputException {
        return reader.read();
    }

    private Integer readComboOperand() throws NoInputException {
        int literal = reader.read();
        return switch (literal) {
            case 0 -> 0;
            case 1 -> 1;
            case 2 -> 2;
            case 3 -> 3;
            case 4 -> register.getA();
            case 5 -> register.getB();
            case 6 -> register.getC();
            default -> throw new IllegalArgumentException("Invalid combo operand");
        };
    }
}
