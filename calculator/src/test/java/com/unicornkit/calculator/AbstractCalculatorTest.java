package com.unicornkit.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractCalculatorTest {
    protected abstract long calculate(String expr);

    @Test
    void testEmpty() {
        assertThrows(CalculatorSyntaxError.class, ()->calculate(""));
    }

    @Test
    void testNonNumber() {
        assertThrows(CalculatorSyntaxError.class, ()->calculate("5+A"));
    }

    @Test
    void testTooManyOperators() {
        assertThrows(CalculatorSyntaxError.class, ()->calculate("5**1"));
    }

    @Test
    void testLiteral() {
        assertEquals(5, calculate("5"));
    }

    @Test
    void testLongLiteral() {
        assertEquals(5568, calculate("5568"));
    }

    @Test
    void testNegativeLiteral() {
        assertEquals(-5, calculate("-5"));
    }

    @Test
    void testHexLiteral() {
        assertEquals(0xABCDEF, calculate("0xABCDEF"));
    }

    @Test
    void testNegativeHexLiteral() {
        assertEquals(-0xABCDEF, calculate("-0xABCDEF"));
    }

    @Test
    void testAdd() {
        assertEquals(10, calculate("5+5"));
        assertEquals(55, calculate("5+50"));
    }

    @Test
    void testSub() {
        assertEquals(0, calculate("5-5"));
        assertEquals(-45, calculate("5-50"));
    }

    @Test
    void testAddNegative() {
        assertEquals(0, calculate("5+-5"));
    }

    @Test
    void testAddMulti() {
        assertEquals(1 + 2 + 3 + 4 + 5, calculate("1+2+3+4+5"));
    }

    @Test
    void testMultiply() {
        assertEquals(8, calculate("2*4"));
    }

    @Test
    void testDivision() {
        assertEquals(5, calculate("10/2"));
    }

    @Test
    void testPrecedence() {
        assertEquals((2 * 4) + (3 * 2), calculate("2*4+3*2"));
    }

    @Test
    void testPrecedence2() {
        assertEquals((2 * 4) + (3 * 2) + 5, calculate("2*4+3*2+5"));
    }

    @Test
    void testParens() {
        assertEquals(2 * (4 + 3) * 2, calculate("2*(4+3)*2"));
    }

    @Test
    void testComplex() {
        assertEquals((1+2)*-0xA/(9-0x05), calculate("(1+2)*-0xA/(9-0x05)"));
    }
}