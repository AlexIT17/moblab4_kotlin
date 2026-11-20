package com.example.moblab4_kotlin

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import kotlin.math.PI
import kotlin.math.atan

class SeriesCalculatorTest {
    @Test fun zeroIncludesFirstTerm() {
        assertEquals(-PI / 2.0 + 0.5, SeriesCalculator.calculate(-2.0, 0), 1e-15)
    }

    @Test fun appliesAlternatingSignsThroughN() {
        val expected = -PI / 2.0 + 0.5 - 1.0 / 24.0 + 1.0 / 160.0
        assertEquals(expected, SeriesCalculator.calculate(-2.0, 2), 1e-15)
    }

    @Test fun partialSumApproachesArctangent() {
        for (x in listOf(-1.01, -2.0, -10.0, -Double.MAX_VALUE)) {
            assertEquals(atan(x), SeriesCalculator.calculate(x, 1000), 1e-11)
        }
    }

    @Test fun rejectsInvalidInputs() {
        for (x in listOf(-1.0, 0.0, Double.NaN, Double.NEGATIVE_INFINITY)) {
            assertThrows(IllegalArgumentException::class.java) { SeriesCalculator.calculate(x, 0) }
        }
        for (n in listOf(-1, SeriesCalculator.MAX_N + 1)) {
            assertThrows(IllegalArgumentException::class.java) { SeriesCalculator.calculate(-2.0, n) }
        }
    }
}
