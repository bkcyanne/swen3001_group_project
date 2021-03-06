package com.example.killmenow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.killmenow.settings
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import kotlinx.android.synthetic.main.activity_settings.*


class settings : AppCompatActivity() {
    lateinit var profileInput:TextView
    lateinit var profileIconInput :ImageView
    lateinit var tasksIcon:BottomNavigationItemView
    lateinit var settingsIcon:TextView
    lateinit var homeIcon:BottomNavigationItemView
    val firstDay: Array<String> =arrayOf("Thursday","Tuesday","Wednesday","Monday","Friday","Saturday","Sunday")
    var languages: Array<String> =arrayOf("English","Español","日本語")
    var themes: Array<String> =arrayOf("Standard","Dark","Purple","Wild")
    lateinit var logout:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        profileInput=findViewById<TextView>(R.id.ProfileClicker)
        profileIconInput=findViewById<ImageView>(R.id.ProfileIcon)
        tasksIcon=findViewById<BottomNavigationItemView>(R.id.tasks)
        settingsIcon=findViewById<TextView>(R.id.settings)
        homeIcon=findViewById<BottomNavigationItemView>(R.id.homePage)

        val langAdapter =ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,languages)
        langSpinner.adapter=langAdapter

        val themeAdapter =ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,themes)
       themeSpinner.adapter=themeAdapter
        val firstDayAdapter =ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,firstDay)
        firstDaySpinner.adapter=firstDayAdapter

        profileInput.setOnClickListener{



            val intent = Intent(this,editProfile::class.java)
            startActivity(intent)

        }

        profileIconInput.setOnClickListener{

            val intent = Intent(this,editProfile::class.java)
            startActivity(intent)
            finish()

        }
        logout=findViewById(R.id.LogoutButton)
        logout.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)

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
        homeIcon.setOnClickListener{

            val intent = Intent(this, WeatherPortion::class.java)
            startActivity(intent)

        }




    }
}