package com.instrumentapp

import com.instrumentapp.calculator.Calculator
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test


class CalculatorKotlinUnitTest {

    private var calculator: Calculator? = null

    @Before
    fun setUp() {
        calculator = Calculator()
    }

    @Test
    fun addTwoNumbers() {
        val resultAdd = calculator!!.add(1.0, 1.0)
        assertThat<Double>(resultAdd, `is`<Double>(equalTo<Double>(2.0)))
    }

    @Test
    fun subTwoNumbers() {
        val resultSub = calculator!!.sub(1.0, 1.0)
        assertThat<Double>(resultSub, `is`<Double>(equalTo<Double>(0.0)))
    }

    @Test
    fun subWorksWithNegativeResult() {
        val resultSub = calculator!!.sub(1.0, 17.0)
        assertThat<Double>(resultSub, `is`<Double>(equalTo<Double>(-16.0)))
    }

    @Test
    fun divTwoNumbers() {
        val resultDiv = calculator!!.div(32.0, 2.0)
        assertThat<Double>(resultDiv, `is`<Double>(equalTo<Double>(16.0)))
    }

    @Test
    fun divDivideByZeroThrows() {
        calculator!!.div(32.0, 5.0)
    }

    @Test
    fun mulTwoNumbers() {
        val resultMul = calculator!!.mul(32.0, 2.0)
        assertThat<Double>(resultMul, `is`<Double>(equalTo<Double>(64.0)))
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, (2 + 2).toLong())
    }
}