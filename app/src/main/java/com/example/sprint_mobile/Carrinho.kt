package com.example.sprint_mobile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.TextView

class Carrinho : AppCompatActivity() {
    private lateinit var cartUtils: CartUtils
    private lateinit var cartAdapter: CartAdapter
    private lateinit var totalTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrinho)

        cartUtils = CartUtils()
        totalTextView = findViewById(R.id.cart_total)

        val cartProducts = cartUtils.getCartProducts(this).toMutableList()

        cartAdapter = CartAdapter(cartProducts, onRemoveClick = { product ->
            removeFromCart(product)
        }, onTotalChanged = { total ->
            updateTotal(total)
        })

        val recyclerView: RecyclerView = findViewById(R.id.cart_items_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = cartAdapter

        val backButton: Button = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }

        updateTotal(cartAdapter.getTotal())
    }

    private fun removeFromCart(product: ProductResult) {
        val currentCart = cartUtils.getCartProducts(this).toMutableList()
        currentCart.remove(product)
        cartUtils.saveCartProducts(this, currentCart)
        cartAdapter.updateCart(currentCart)
        updateTotal(cartAdapter.getTotal())
    }


    private fun updateTotal(total: Double) {
        totalTextView.text = "Total: R$ ${String.format("%.2f", total)}"
    }
}
