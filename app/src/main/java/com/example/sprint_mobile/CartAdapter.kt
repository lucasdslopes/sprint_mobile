package com.example.sprint_mobile

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(
    private var cartProducts: MutableList<ProductResult>,
    private val onRemoveClick: (ProductResult) -> Unit,
    private val onTotalChanged: (Double) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.cart_item_title)
        val price: TextView = view.findViewById(R.id.cart_item_price)
        val removeButton: Button = view.findViewById(R.id.button_remove_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val product = cartProducts[position]
        holder.title.text = product.title
        holder.price.text = product.price

        // Configura o botão de remover
        holder.removeButton.setOnClickListener {
            onRemoveClick(product)
            removeItem(holder.adapterPosition)
        }

        holder.itemView.setOnClickListener {
            val url = product.link
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            holder.itemView.context.startActivity(intent)
        }
    }

    private fun removeItem(position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            cartProducts.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, cartProducts.size)
            recalculateTotal()
        }
    }

    private fun recalculateTotal() {
        val total = cartProducts.sumOf {
            it.price.replace("R$", "").replace(".", "").replace(",", ".").trim().toDoubleOrNull() ?: 0.0
        }
        onTotalChanged(total)
    }

    fun getTotal(): Double {
        return cartProducts.sumOf {
            it.price.replace("R$", "").replace(".", "").replace(",", ".").trim().toDoubleOrNull() ?: 0.0
        }
    }

    override fun getItemCount(): Int = cartProducts.size

    fun updateCart(updatedCart: MutableList<ProductResult>) {
        this.cartProducts = updatedCart
        notifyDataSetChanged()
        recalculateTotal()
    }
}
