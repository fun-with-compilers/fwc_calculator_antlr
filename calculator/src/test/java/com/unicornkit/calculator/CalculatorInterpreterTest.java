package com.unicornkit.calculator;

public class CalculatorInterpreterTest extends AbstractCalculatorTest {
    @Override
    protected long calculate(String expr) {
        return CalculatorFactory.calculate(expr);
    }
}
