package com.example.sprint_mobile

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var nomeInput: EditText? = null
    private var emailInput: EditText? = null
    private var senhaInput: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        nomeInput = findViewById(R.id.ID1_Nome_Text)
        emailInput = findViewById(R.id.ID1_Email_Text)
        senhaInput = findViewById(R.id.ID1_Senha_Text)

        val botaoCadastrar: Button = findViewById(R.id.ID1_Cadastrar_Button)

        botaoCadastrar.setOnClickListener {
            val nome = nomeInput?.text.toString()
            val email = emailInput?.text.toString()
            val senha = senhaInput?.text.toString()

            if (nome.isNotEmpty() && email.isNotEmpty() && senha.isNotEmpty()) {
                val sharedPref = getSharedPreferences("CadastroPrefs", Context.MODE_PRIVATE)
                with(sharedPref.edit()) {
                    putString(email, senha)
                    apply()
                }

                nomeInput?.text?.clear()
                emailInput?.text?.clear()
                senhaInput?.text?.clear()
            }
        }

        botaoCadastrar.setOnClickListener {
            val nome = nomeInput?.text.toString()
            val email = emailInput?.text.toString()
            val senha = senhaInput?.text.toString()

            if (nome.isNotEmpty() && email.isNotEmpty() && senha.isNotEmpty()) {
                val sharedPref = getSharedPreferences("CadastroPrefs", Context.MODE_PRIVATE)
                with(sharedPref.edit()) {
                    putString("nome", nome)
                    putString(email, senha)
                    apply()
                }

                nomeInput?.text?.clear()
                emailInput?.text?.clear()
                senhaInput?.text?.clear()
            }
        }

        val voltarText: TextView = findViewById(R.id.ID1_Voltar_Text)
        voltarText.setOnClickListener {
            finish()
        }
    }
}
