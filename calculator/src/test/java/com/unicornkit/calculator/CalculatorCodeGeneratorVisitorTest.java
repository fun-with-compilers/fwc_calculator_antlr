package com.unicornkit.calculator;

final class CalculatorCodeGeneratorVisitorTest extends AbstractCalculatorTest {
    @Override
    protected long calculate(String expr) {
        return CalculatorFactory.execute(expr);
    }
}