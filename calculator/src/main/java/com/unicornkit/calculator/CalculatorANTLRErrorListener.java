package com.unicornkit.calculator;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

import java.util.BitSet;

final class CalculatorANTLRErrorListener implements ANTLRErrorListener {

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        throw new CalculatorSyntaxError(line, charPositionInLine, msg);
    }

    @Override
    public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex,
                                boolean exact, BitSet ambigAlts, ATNConfigSet configs) {
        throw new CalculatorException("Ambiguous symbol");
    }

    @Override
    public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex,
                                            BitSet conflictingAlts, ATNConfigSet configs) {
        throw new CalculatorException("Attempting full context error");
    }

    @Override
    public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction,
                                         ATNConfigSet configs) {
        throw new CalculatorException("Context sensitivity error");
    }
}
