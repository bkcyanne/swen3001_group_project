package com.example.jumpstart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle;
import android.view.MotionEvent
import android.view.View;
import android.widget.Button;
import android.widget.EditText
import android.widget.TextView;
import android.widget.Toast
import androidx.core.graphics.toColorInt
import kotlinx.android.synthetic.main.activity_main.view.*
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {
    lateinit var username: String
    lateinit var password: String
    lateinit var nameInput : EditText
    lateinit var passwordInput: EditText
    var counter: Int = 2
    lateinit var displayMessage: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameInput= findViewById(R.id.etUsername)
        passwordInput= findViewById(R.id.etPassword)
        for(x in 0 until 2) {
            val logButton = findViewById<Button>(R.id.loginButton)
            logButton.setOnClickListener {
                username = nameInput.text.toString()
                password = passwordInput.text.toString()
                if (username == "AdminCorey" && password == "tasteepatty") {

                    val intent = Intent(this, AccCreationPage::class.java)

                    startActivity(intent)

                } else {
                    Toast.makeText(this, "Invalid Credentials $counter attempts remaining", Toast.LENGTH_SHORT
                    ).show()
                    counter--
                }
            }

        }
        val accCreateText = findViewById<EditText>(R.id.accCreation)
        accCreateText.setOnClickListener{
            val intent = Intent(this, AccCreationPage::class.java)

            startActivity(intent)

        }
    }

}