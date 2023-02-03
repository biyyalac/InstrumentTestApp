package com.instrumentapp;

import com.instrumentapp.calculator.Calculator;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CalculatorJavaUnitTest {

    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void addTwoNumbers() {
        double resultAdd = calculator.add(1d, 1d);
        assertThat(resultAdd, is(equalTo(2d)));
    }

    @Test
    public void subTwoNumbers() {
        double resultSub = calculator.sub(1d, 1d);
        assertThat(resultSub, is(equalTo(0d)));
    }

    @Test
    public void subWorksWithNegativeResult() {
        double resultSub = calculator.sub(1d, 17d);
        assertThat(resultSub, is(equalTo(-16d)));
    }

    @Test
    public void divTwoNumbers() {
        double resultDiv = calculator.div(32d,2d);
        assertThat(resultDiv, is(equalTo(16d)));
    }

    @Test
    public void divDivideByZeroThrows() {
        calculator.div(32d,5d);
    }

    @Test
    public void mulTwoNumbers() {
        double resultMul = calculator.mul(32d, 2d);
        assertThat(resultMul, is(equalTo(64d)));
    }

    @Test
    public void addition_isCorrect(){
        assertEquals(4, 2 + 2);
    }
}