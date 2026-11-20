package com.example.moblab4_kotlin

import kotlin.math.PI

object SeriesCalculator {
    const val MAX_N = 1_000_000

    fun calculate(x: Double, n: Int): Double {
        require(x.isFinite() && x < -1.0)
        require(n in 0..MAX_N)

        var sum = -PI / 2.0
        var term = -1.0 / x
        for (i in 0..n) {
            sum += term
            term = -term / x / x * ((2.0 * i + 1.0) / (2.0 * i + 3.0))
        }
        return sum
    }
}
