package com.example.sprint_mobile

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ResultAdapter(
    private val results: List<ProductResult>,
    private val onAddClick: (ProductResult) -> Unit
) : RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {

    inner class ResultViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.result_title)
        val price: TextView = view.findViewById(R.id.result_price)
        val addButton: Button = view.findViewById(R.id.button_add_to_cart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.result_item, parent, false)
        return ResultViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val product = results[position]
        holder.title.text = product.title
        holder.price.text = product.price

        holder.addButton.setOnClickListener {
            onAddClick(product)
        }
        holder.itemView.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(product.link))
            holder.itemView.context.startActivity(browserIntent)
        }
    }

    override fun getItemCount(): Int = results.size
}
