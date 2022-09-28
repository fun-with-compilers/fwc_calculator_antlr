package com.unicornkit.calculator;

import com.unicornkit.calculator.antlr.CalculatorBaseVisitor;
import com.unicornkit.calculator.antlr.CalculatorParser;

final class CalculatorCodeGeneratorVisitor extends CalculatorBaseVisitor<CodeSegment> {

    private final CodeSegment code = new CodeSegment();

    @Override
    public CodeSegment visitExpression(CalculatorParser.ExpressionContext ctx) {
        visit(ctx.operands.get(0));
        for (var index = 0; index < ctx.operators.size(); ++index) {
            visit(ctx.operands.get(index + 1));
            var operator = ctx.add_op().get(index).getText();
            switch (operator) {
                case "+" -> code.add();
                case "-" -> code.sub();
                default -> throw new IllegalStateException();
            }
        }
        return code;
    }

    @Override
    public CodeSegment visitTerm(CalculatorParser.TermContext ctx) {
        visit(ctx.operands.get(0));
        for (var index = 0; index < ctx.operators.size(); ++index) {
            visit(ctx.operands.get(index + 1));
            var operator = ctx.mul_op().get(index).getText();
            switch (operator) {
                case "*" -> code.mul();
                case "/" -> code.div();
                default -> throw new IllegalStateException();
            }
        }
        return code;
    }

    @Override
    public CodeSegment visitFactor(CalculatorParser.FactorContext ctx) {
        if (ctx.num != null) {
            visit(ctx.num);
        }
        if (ctx.expr != null) {
            visit(ctx.expr);
        }
        if (ctx.neg != null) {
            code.neg();
        }
        return code;
    }

    @Override
    public CodeSegment visitNumber(CalculatorParser.NumberContext ctx) {
        if (ctx.dec != null) {
            code.push(Long.parseLong(ctx.dec.getText()));
            return code;
        }
        if (ctx.hex != null) {
            long arg = Long.parseLong(ctx.hex.getText().replace("0x", ""), 16);
            code.push(arg);
            return code;
        }
        throw new IllegalStateException();
    }
}
