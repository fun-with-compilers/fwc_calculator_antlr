package com.unicornkit.calculator;

record Instruction(String opcode, Object operand) {
    @Override
    public String toString() {
        return operand != null ? opcode + ' ' + operand : opcode;
    }
}

