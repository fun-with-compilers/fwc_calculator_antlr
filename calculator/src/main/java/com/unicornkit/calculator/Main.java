package com.unicornkit.calculator;

import java.util.Scanner;

public class Main {
    public static String input(String message) {
        var sc= new Scanner(System.in);
        System.out.print(message);
        return sc.nextLine();
    }

    public static void main(String[] args) {
        var formulae = 2 <= args.length ? args[1] : input("Expression: ");
        var result = CalculatorFactory.calculate(formulae);
        var code = CalculatorFactory.generate(formulae);
        System.out.printf("%1s = %2d", formulae, result);
        System.out.println();
        System.out.println();
        System.out.println(code);
    }
}