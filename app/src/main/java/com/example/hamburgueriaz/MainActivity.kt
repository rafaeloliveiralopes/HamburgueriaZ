package com.example.hamburgueriaz

import android.content.Intent
import android.os.Bundle
import android.net.Uri
import android.graphics.Paint
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
        btnPlaceOrder.paintFlags = btnPlaceOrder.paintFlags and Paint.UNDERLINE_TEXT_FLAG.inv()
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
        btnPlaceOrder.setOnClickListener { enviarPedido() }
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

    private fun enviarPedido() {
        val order = currentOrder()

        when {
            order.clientName.isBlank() -> {
                etClientName.error = getString(R.string.error_missing_name)
                etClientName.requestFocus()
            }
            order.quantity == 0 -> {
                tvSummary.text = getString(R.string.error_missing_quantity)
                tvTotalPrice.text = getString(R.string.default_total_price)
            }
            else -> {
                tvSummary.text = order.summary
                tvTotalPrice.text = OrderCalculator.formatCurrency(order.totalPrice)
                abrirEmail(order)
            }
        }
    }

    private fun abrirEmail(order: OrderDetails) {
        val emailSubject = getString(R.string.email_subject_template, order.clientName)

        val sendToIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_SUBJECT, emailSubject)
            putExtra(Intent.EXTRA_TEXT, order.summary)
        }

        if (sendToIntent.resolveActivity(packageManager) != null) {
            startActivity(sendToIntent)
            return
        }

        val fallbackIntent = Intent(Intent.ACTION_SEND).apply {
            type = "message/rfc822"
            putExtra(Intent.EXTRA_SUBJECT, emailSubject)
            putExtra(Intent.EXTRA_TEXT, order.summary)
        }

        if (fallbackIntent.resolveActivity(packageManager) != null) {
            startActivity(
                Intent.createChooser(fallbackIntent, getString(R.string.email_chooser_title))
            )
        } else {
            Toast.makeText(this, R.string.error_missing_email_app, Toast.LENGTH_SHORT).show()
        }
    }

    private fun renderOrder() {
        val order = currentOrder()

        tvQuantity.text = quantity.toString()
        tvSummary.text = if (order.quantity == 0) {
            getString(R.string.default_summary)
        } else {
            order.summary
        }
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
