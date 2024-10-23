package com.example.sprint_mobile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val emailInput: EditText = findViewById(R.id.ID1_Email_Login)
        val senhaInput: EditText = findViewById(R.id.ID1_Senha_Login)
        val loginButton: Button = findViewById(R.id.ID1_Login_Button)

        loginButton.setOnClickListener {
            val email = emailInput.text.toString()
            val senha = senhaInput.text.toString()

            val sharedPref = getSharedPreferences("CadastroPrefs", Context.MODE_PRIVATE)
            val senhaCadastrada = sharedPref.getString(email, null)

            if (senhaCadastrada != null && senhaCadastrada == senha) {
                val intent = Intent(this, Home::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Email ou senha incorretos. Verifique seu cadastro.", Toast.LENGTH_SHORT).show()
            }
        }

        val cadastroText: TextView = findViewById(R.id.ID1_Cadastro_Link)
        cadastroText.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
