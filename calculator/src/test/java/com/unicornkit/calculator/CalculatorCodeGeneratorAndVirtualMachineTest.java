package com.unicornkit.calculator;

final class CalculatorCodeGeneratorAndVirtualMachineTest extends AbstractCalculatorTest {
    @Override
    protected long calculate(String expr) {
        return CalculatorFactory.execute(expr);
    }
}