package com.unicornkit.calculator;

import com.unicornkit.calculator.antlr.CalculatorBaseVisitor;
import com.unicornkit.calculator.antlr.CalculatorParser;

final class CalculatorInterpreterVisitor extends CalculatorBaseVisitor<Long> {

    @Override
    public Long visitExpression(CalculatorParser.ExpressionContext ctx) {
        long result = visit(ctx.operands.get(0));
        for (var index = 0; index < ctx.operators.size(); ++index) {
            long operand = visit(ctx.operands.get(index + 1));
            var operator = ctx.add_op().get(index).getText();
            switch (operator) {
                case "+" -> result += operand;
                case "-" -> result -= operand;
                default -> throw new IllegalStateException();
            }
        }
        return result;
    }

    @Override
    public Long visitTerm(CalculatorParser.TermContext ctx) {
        long result = visit(ctx.operands.get(0));
        for (var index = 0; index < ctx.operators.size(); ++index) {
            long operand = visit(ctx.operands.get(index + 1));
            var operator = ctx.mul_op().get(index).getText();
            switch (operator) {
                case "*" -> result *= operand;
                case "/" -> result /= operand;
                default -> throw new IllegalStateException();
            }
        }
        return result;
    }

    @Override
    public Long visitFactor(CalculatorParser.FactorContext ctx) {
        long result = ctx.num != null ? visit(ctx.num) : visit(ctx.expr);
        return ctx.neg != null ? -result : result;
    }

    @Override
    public Long visitNumber(CalculatorParser.NumberContext ctx) {
        if (ctx.dec != null) {
            return Long.parseLong(ctx.dec.getText());
        }
        if (ctx.hex != null) {
            return Long.parseLong(ctx.hex.getText().replace("0x", ""), 16);
        }
        return null;
    }
}
