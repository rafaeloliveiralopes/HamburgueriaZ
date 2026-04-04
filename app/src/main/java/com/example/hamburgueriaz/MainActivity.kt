package com.example.hamburgueriaz

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private var quantity = 0

    private lateinit var etClientName: EditText
    private lateinit var cbBacon: CheckBox
    private lateinit var cbCheese: CheckBox
    private lateinit var cbOnionRings: CheckBox
    private lateinit var tvQuantity: TextView
    private lateinit var tvSummary: TextView
    private lateinit var tvTotalPrice: TextView
    private lateinit var btnDecrease: Button
    private lateinit var btnIncrease: Button
    private lateinit var btnPlaceOrder: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        bindViews()
        bindListeners()
        renderOrder()
    }

    private fun bindViews() {
        etClientName = findViewById(R.id.etClientName)
        cbBacon = findViewById(R.id.cbBacon)
        cbCheese = findViewById(R.id.cbCheese)
        cbOnionRings = findViewById(R.id.cbOnionRings)
        tvQuantity = findViewById(R.id.tvQuantity)
        tvSummary = findViewById(R.id.tvSummary)
        tvTotalPrice = findViewById(R.id.tvTotalPrice)
        btnDecrease = findViewById(R.id.btnDecrease)
        btnIncrease = findViewById(R.id.btnIncrease)
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder)
    }

    private fun bindListeners() {
        btnDecrease.setOnClickListener { subtrair() }
        btnIncrease.setOnClickListener { somar() }

        val optionChangeListener = {
            renderOrder()
        }

        cbBacon.setOnCheckedChangeListener { _, _ -> optionChangeListener() }
        cbCheese.setOnCheckedChangeListener { _, _ -> optionChangeListener() }
        cbOnionRings.setOnCheckedChangeListener { _, _ -> optionChangeListener() }

        btnPlaceOrder.setOnClickListener {
            val order = currentOrder()

            when {
                order.clientName.isBlank() -> {
                    etClientName.error = getString(R.string.error_missing_name)
                    etClientName.requestFocus()
                }
                order.quantity == 0 -> {
                    Toast.makeText(this, R.string.error_missing_quantity, Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(
                        this,
                        getString(
                            R.string.order_success_message,
                            order.clientName,
                            OrderCalculator.formatCurrency(order.totalPrice)
                        ),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun somar() {
        quantity++
        renderOrder()
    }

    private fun subtrair() {
        if (quantity == 0) {
            return
        }

        quantity--
        renderOrder()
    }

    private fun renderOrder() {
        val order = currentOrder()
        tvQuantity.text = order.quantity.toString()
        tvSummary.text = order.summary
        tvTotalPrice.text = OrderCalculator.formatCurrency(order.totalPrice)
    }

    private fun currentOrder(): OrderDetails {
        return OrderCalculator.calculateOrder(
            clientName = etClientName.text.toString().trim(),
            quantity = quantity,
            withBacon = cbBacon.isChecked,
            withCheese = cbCheese.isChecked,
            withOnionRings = cbOnionRings.isChecked
        )
    }
}
