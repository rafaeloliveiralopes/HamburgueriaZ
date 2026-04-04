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
        assertEquals(48.0, result.totalPrice, 0.0)
        assertTrue(result.summary.contains("Rafael"))
        assertTrue(result.summary.contains("Tem Bacon? Sim"))
        assertTrue(result.summary.contains("Tem Queijo? Sim"))
        assertTrue(result.summary.contains("Tem Onion Rings? Não"))
        assertTrue(result.summary.contains("Quantidade: 2"))
        assertTrue(
            result.summary.contains("Preço final: ${OrderCalculator.formatCurrency(48.0)}")
        )
    }
}
