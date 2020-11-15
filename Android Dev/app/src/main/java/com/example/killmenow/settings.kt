package com.example.killmenow

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView


class settings : AppCompatActivity() {
    lateinit var profileInput:TextView
    lateinit var profileIconInput :ImageView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        profileInput=findViewById<TextView>(R.id.ProfileClicker)
        profileIconInput=findViewById<ImageView>(R.id.ProfileIcon)

        profileInput.setOnClickListener{

            val intent = Intent(this,profile::class.java)
            startActivity(intent)

        }

        profileIconInput.setOnClickListener{

            val intent = Intent(this,profile::class.java)
            startActivity(intent)

        }
    }
}