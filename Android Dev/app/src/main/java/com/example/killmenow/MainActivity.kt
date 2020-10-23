package com.example.killmenow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Thread.sleep


class MainActivity : AppCompatActivity() {
    lateinit var uN: String
    lateinit var pW: String
    lateinit var uNInput: EditText
    lateinit var pWInput: EditText
    private var counter :Int =2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        uNInput = findViewById<EditText>(R.id.Username)
        pWInput = findViewById<EditText>(R.id.Password)

        for(x in 0 until 2) {
            val loginAccButton = findViewById<Button>(R.id.loginButton)
            loginAccButton.setOnClickListener {
                uN = uNInput.text.toString()
                pW = pWInput.text.toString()

                if (uN == "AdminCorey" && pW == "tasteepatty") {

                    Toast.makeText(this, "Welcome back $uN!", Toast.LENGTH_SHORT).show()
                    sleep(3000)
                    val intent = Intent(this, newTask::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this, "Invalid Credentials, $counter attempts remaining", Toast.LENGTH_SHORT).show()
                    counter--
                }
            }
        }
        val noAccButton =findViewById<Button>(R.id.noAccButton)
        noAccButton.setOnClickListener{
            val intent = Intent(this, accCreationPage::class.java)
            startActivity(intent)

        }


    }

}
