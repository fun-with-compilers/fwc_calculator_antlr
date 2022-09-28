package com.unicornkit.calculator;

import java.util.ArrayList;
import java.util.List;

record Instruction(String opcode, Object operand) {
    @Override
    public String toString() {
        return operand != null ? opcode + ' ' + operand.toString() : opcode;
    }
}

