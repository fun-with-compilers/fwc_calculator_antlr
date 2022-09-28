package com.unicornkit.calculator;

import java.util.ArrayList;
import java.util.List;

final class CodeSegment {
    private final List<Instruction> instructions = new ArrayList<>();

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public void add() {
        addInstruction("ADD");
    }

    public void sub() {
        addInstruction("SUB");
    }

    public void mul() {
        addInstruction("MUL");
    }

    public void div() {
        addInstruction("DIV");
    }

    public void neg() {
        push(-1);
        mul();
    }

    public void push(long value) {
        addInstruction("PUSH", value);
    }

    public void addInstruction(String opcode) {
        addInstruction(opcode, null);
    }

    public void addInstruction(String opcode, Object operand) {
        instructions.add(new Instruction(opcode, operand));
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        getInstructions().forEach(i -> sb.append(i).append('\n'));
        return sb.toString();
    }
}
