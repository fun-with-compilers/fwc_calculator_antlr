package com.unicornkit.calculator;

import java.util.Objects;
import java.util.Stack;

public final class VirtualMachine {
    private final Stack<Long> stack = new Stack<>();

    private final CodeSegment code;

    private VirtualMachine(CodeSegment code) {
        this.code = code;
    }

    public static long execute(CodeSegment code) {
        var vm = new VirtualMachine(code);
        return vm.execute();
    }

    private void push(Object arg) {
        Objects.requireNonNull(arg);
        stack.push((Long)arg);
    }

    private long pop() {
        return stack.pop();
    }

    private void add() {
        long right = pop();
        long left = pop();
        push(left + right);
    }

    private void sub() {
        long right = pop();
        long left = pop();
        push(left - right);
    }

    private void mul() {
        long right = pop();
        long left = pop();
        push(left * right);
    }

    private void div() {
        long right = pop();
        long left = pop();
        push(left / right);
    }

    private long execute() {
        code.getInstructions().forEach(i->{
            switch (i.opcode()) {
                case "PUSH" -> push(i.operand());
                case "ADD" -> add();
                case "SUB" -> sub();
                case "MUL" -> mul();
                case "DIV" -> div();
            }
        });
        return pop();
    }
}
