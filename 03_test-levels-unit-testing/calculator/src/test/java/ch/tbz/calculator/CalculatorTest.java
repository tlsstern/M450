package ch.tbz.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorTest {

    private static final double DELTA = 0.0001;

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void addTwoPositiveNumbers() {
        assertEquals(5.5, calculator.add(2.5, 3.0), DELTA);
    }

    @Test
    void addNegativeNumber() {
        assertEquals(-1.0, calculator.add(2.0, -3.0), DELTA);
    }

    @Test
    void subtractTwoNumbers() {
        assertEquals(1.5, calculator.subtract(4.0, 2.5), DELTA);
    }

    @Test
    void subtractToNegativeResult() {
        assertEquals(-2.0, calculator.subtract(1.0, 3.0), DELTA);
    }

    @Test
    void multiplyTwoNumbers() {
        assertEquals(7.5, calculator.multiply(2.5, 3.0), DELTA);
    }

    @Test
    void multiplyWithZero() {
        assertEquals(0.0, calculator.multiply(5.0, 0.0), DELTA);
    }

    @Test
    void divideTwoNumbers() {
        assertEquals(2.5, calculator.divide(5.0, 2.0), DELTA);
    }

    @Test
    void divideWithPeriodicResult() {
        assertEquals(0.3333, calculator.divide(1.0, 3.0), DELTA);
    }

    @Test
    void divideByZeroThrowsException() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(5.0, 0.0));
    }
}
