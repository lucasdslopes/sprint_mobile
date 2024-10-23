package com.example.sprint_mobile

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val searchIcon = findViewById<ImageView>(R.id.home_search_icon) // ID da lupa no layout
        searchIcon.setOnClickListener {
            val intent = Intent(this, Pesquisa::class.java)
            startActivity(intent)
        }

        val cartIcon = findViewById<ImageView>(R.id.home_cart_icon) // ID do carrinho no layout
        cartIcon.setOnClickListener {
            val intent = Intent(this, Carrinho::class.java)
            startActivity(intent)
        }
    }
}
