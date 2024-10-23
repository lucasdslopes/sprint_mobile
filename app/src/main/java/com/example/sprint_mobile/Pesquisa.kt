package com.example.sprint_mobile

import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class Pesquisa : AppCompatActivity() {

    private lateinit var resultsRecyclerView: RecyclerView
    private lateinit var cartUtils: CartUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesquisa)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        cartUtils = CartUtils()

        val searchInput: EditText = findViewById(R.id.search_input)
        val enviarButton: Button = findViewById(R.id.button_enviar)
        resultsRecyclerView = findViewById(R.id.results_recycler_view)
        resultsRecyclerView.layoutManager = LinearLayoutManager(this)

        enviarButton.setOnClickListener {
            val query = searchInput.text.toString().trim()
            if (query.isNotEmpty()) {
                val results = searchProducts(query)
                results?.let {
                    displayResults(it)
                } ?: run {
                    Toast.makeText(this, "Nenhum resultado encontrado.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Digite um termo de pesquisa.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun displayResults(results: List<ProductResult>) {
        val adapter = ResultAdapter(results) { product ->
            addToCart(product)
        }
        resultsRecyclerView.adapter = adapter
    }

    private fun addToCart(product: ProductResult) {
        val currentCart = cartUtils.getCartProducts(this).toMutableList()

        val productAlreadyInCart = currentCart.any { it.title == product.title }

        if (!productAlreadyInCart) {
            currentCart.add(product)
            cartUtils.saveCartProducts(this, currentCart)
            Toast.makeText(this, "${product.title} foi adicionado ao carrinho.", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "${product.title} já está no carrinho.", Toast.LENGTH_SHORT).show()
        }
    }


    private fun searchProducts(query: String): List<ProductResult>? {
        val apiKey = "1a316514948c0ae8d114b67d500a23bd5282e4564b67ca6c0fa0bd1d1f296e64"
        val urlString = "https://serpapi.com/search.json?q=$query&tbm=shop&engine=google_shopping&gl=br&hl=pt&api_key=$apiKey"

        return try {
            val url = URL(urlString)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"

            if (connection.responseCode == 200) {
                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                val response = reader.readText()
                reader.close()

                val jsonResults = JSONObject(response)
                val shoppingResults = jsonResults.optJSONArray("shopping_results")

                shoppingResults?.let {
                    val productList = mutableListOf<ProductResult>()
                    for (i in 0 until it.length()) {
                        val product = it.getJSONObject(i)
                        val title = product.optString("title", "Sem título")
                        val price = product.optString("price", "Sem preço")
                        val link = product.optString("link", "")
                        productList.add(ProductResult(title, price, link))
                    }
                    productList
                } ?: emptyList()

            } else {
                Log.e("API_ERROR", "Erro na resposta da API: Código ${connection.responseCode}")
                null
            }

        } catch (e: Exception) {
            Log.e("API_ERROR", "Erro na requisição: ${e.message}")
            return null
        }
    }
}
