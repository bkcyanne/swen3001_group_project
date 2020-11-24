package com.example.killmenow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationItemView


class settings : AppCompatActivity() {
    lateinit var profileInput:TextView
    lateinit var profileIconInput :ImageView
    lateinit var tasksIcon:BottomNavigationItemView
    lateinit var settingsIcon:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        profileInput=findViewById<TextView>(R.id.ProfileClicker)
        profileIconInput=findViewById<ImageView>(R.id.ProfileIcon)
        tasksIcon=findViewById<BottomNavigationItemView>(R.id.tasks)
        settingsIcon=findViewById<TextView>(R.id.settings)
        profileInput.setOnClickListener{

            val intent = Intent(this,HomePage::class.java)
            startActivity(intent)

        }

        profileIconInput.setOnClickListener{

            val intent = Intent(this,WeatherPortion::class.java)
            startActivity(intent)
            finish()

        }


        tasksIcon.setOnClickListener{

            val intent = Intent(this, viewAllTasks::class.java)
            startActivity(intent)
            finish()

        }
        settingsIcon.setOnClickListener{

            val intent = Intent(this, settings::class.java)
            startActivity(intent)

        }



    }
}