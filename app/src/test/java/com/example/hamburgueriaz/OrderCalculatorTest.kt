package com.example.hamburgueriaz

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class OrderCalculatorTest {
    @Test
    fun calculateOrder_returnsZeroTotalWhenQuantityIsZero() {
        val result = OrderCalculator.calculateOrder(
            clientName = "Rafael",
            quantity = 0,
            withBacon = false,
            withCheese = false,
            withOnionRings = false
        )

        assertEquals(0.0, result.totalPrice, 0.0)
        assertEquals("Nenhum item selecionado.", result.summary)
    }

    @Test
    fun calculateOrder_includesSelectedExtrasInSummaryAndTotal() {
        val result = OrderCalculator.calculateOrder(
            clientName = "Rafael",
            quantity = 2,
            withBacon = true,
            withCheese = true,
            withOnionRings = false
        )

        assertEquals(listOf("Bacon", "Queijo"), result.extras)
        assertEquals(45.0, result.totalPrice, 0.0)
        assertTrue(result.summary.contains("Bacon"))
        assertTrue(result.summary.contains("Queijo"))
    }
}
