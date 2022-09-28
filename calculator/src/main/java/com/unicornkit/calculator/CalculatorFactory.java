package com.unicornkit.calculator;

import com.unicornkit.calculator.antlr.CalculatorLexer;
import com.unicornkit.calculator.antlr.CalculatorParser;
import com.unicornkit.calculator.antlr.CalculatorVisitor;
import org.antlr.v4.runtime.*;

public final class CalculatorFactory {

    private CalculatorFactory() {

    }

    public static CalculatorParser createParserFromString(String source) {
        var charStream = CharStreams.fromString(source);
        var lexer = new CalculatorLexer(charStream);
        var tokens = new CommonTokenStream(lexer);
        var parser = new CalculatorParser(tokens);
        var errorListener = new CalculatorANTLRErrorListener();
        lexer.addErrorListener(errorListener);
        parser.addErrorListener(errorListener);
        return parser;
    }

    public static <T> T traverse(CalculatorParser parser, CalculatorVisitor<T> visitor) {
        var context = parser.expression();
        return visitor.visitExpression(context);
    }

    public static Long calculate(CalculatorParser parser) {
        return traverse(parser, new CalculatorInterpreterVisitor());
    }

    public static long calculate(String formulae) {
        var parser = createParserFromString(formulae);
        return calculate(parser);
    }

    public static String generate(String formulae) {
        var parser = createParserFromString(formulae);
        var codeSegment = traverse(parser, new CalculatorCodeGeneratorVisitor());
        return codeSegment.toString();
    }

    public static long execute(String formulae) {
        var parser = createParserFromString(formulae);
        var codeSegment = traverse(parser, new CalculatorCodeGeneratorVisitor());
        return VirtualMachine.execute(codeSegment);

    }

}
