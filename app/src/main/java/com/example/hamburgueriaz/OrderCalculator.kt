package com.example.hamburgueriaz

import java.text.NumberFormat
import java.util.Locale

data class OrderDetails(
    val clientName: String,
    val quantity: Int,
    val extras: List<String>,
    val totalPrice: Double,
    val summary: String
)

object OrderCalculator {
    private const val BURGER_PRICE = 20.0
    private const val BACON_PRICE = 2.0
    private const val CHEESE_PRICE = 2.0
    private const val ONION_RINGS_PRICE = 3.0

    fun calculateOrder(
        clientName: String,
        quantity: Int,
        withBacon: Boolean,
        withCheese: Boolean,
        withOnionRings: Boolean
    ): OrderDetails {
        val extras = buildList {
            if (withBacon) add("Bacon")
            if (withCheese) add("Queijo")
            if (withOnionRings) add("Onion Rings")
        }

        val unitPrice = BURGER_PRICE +
            (if (withBacon) BACON_PRICE else 0.0) +
            (if (withCheese) CHEESE_PRICE else 0.0) +
            (if (withOnionRings) ONION_RINGS_PRICE else 0.0)

        val totalPrice = quantity * unitPrice

        val summary = if (quantity == 0) {
            "Nenhum item selecionado."
        } else {
            buildString {
                appendLine(clientName)
                appendLine("Tem Bacon? ${if (withBacon) "Sim" else "Não"}")
                appendLine("Tem Queijo? ${if (withCheese) "Sim" else "Não"}")
                appendLine("Tem Onion Rings? ${if (withOnionRings) "Sim" else "Não"}")
                appendLine("Quantidade: $quantity")
                append("Preço final: ${formatCurrency(totalPrice)}")
            }
        }

        return OrderDetails(
            clientName = clientName,
            quantity = quantity,
            extras = extras,
            totalPrice = totalPrice,
            summary = summary
        )
    }

    fun formatCurrency(value: Double): String {
        return NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR")).format(value)
    }
}
