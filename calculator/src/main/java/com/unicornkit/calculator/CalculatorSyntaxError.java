package com.unicornkit.calculator;

public class CalculatorSyntaxError extends CalculatorException {

    private final int line;
    private final int charPositionInLine;

    public CalculatorSyntaxError(int line, int charPositionInLine, String msg) {
        super(msg);
        this.line = line;
        this.charPositionInLine = charPositionInLine;
    }

    @Override
    public String toString() {
        return String.format("ERROR(%d:%d): %s", line, charPositionInLine, getMessage());
    }
}
