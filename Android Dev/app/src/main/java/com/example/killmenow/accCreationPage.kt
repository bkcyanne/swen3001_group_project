package com.example.killmenow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.lang.Thread.sleep

class accCreationPage : AppCompatActivity() {
    lateinit var fN: String
    lateinit var email: String
    lateinit var caPassword: String
    lateinit var caPasswordConf: String

    lateinit var fNInput: EditText
    lateinit var emailInput: EditText
    lateinit var caPasswordInput: EditText
    lateinit var caPassworConfInput: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acc_creation_page)
        fNInput = findViewById(R.id.fullName)
        emailInput = findViewById(R.id.email)
        caPasswordInput = findViewById(R.id.cAPassword)
        caPassworConfInput = findViewById(R.id.cAPasswordConf)
        val emailPattern = Regex("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")
        val createAccButton = findViewById<Button>(R.id.createButton)
        createAccButton.setOnClickListener {
            fN = fNInput.text.toString()
            email = emailInput.text.toString()
            caPassword = caPasswordInput.text.toString()
            caPasswordConf = caPassworConfInput.text.toString()

            if (!emailPattern.containsMatchIn(email)) {

                Toast.makeText(this, "Please enter valid Email address", Toast.LENGTH_SHORT).show()
            } else if (caPassword.length <= 5) {
                Toast.makeText(this, "Password must be 6 digits or more", Toast.LENGTH_SHORT).show()

            } else if (caPassword != caPasswordConf) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()

            } else {

                Toast.makeText(this, "Account Successfully Created", Toast.LENGTH_SHORT).show()
                sleep(3000)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            }

        }
    }
}