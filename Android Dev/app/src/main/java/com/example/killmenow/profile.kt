package com.example.killmenow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView


class profile : AppCompatActivity() {
    lateinit var editProfileIcon:ImageView


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        editProfileIcon=findViewById<ImageView>(R.id.editPencilIcon)
        editProfileIcon.setOnClickListener{

            val intent = Intent(this, editProfile::class.java)
            startActivity(intent)

        }
    }
}