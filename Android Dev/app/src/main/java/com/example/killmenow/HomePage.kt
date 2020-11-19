package com.example.killmenow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.solver.widgets.WidgetContainer
import androidx.lifecycle.ViewModelProvider
import com.example.database.User
import com.example.database.UserViewModel
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.internal.NavigationMenuItemView
import com.google.android.material.navigation.NavigationView
import java.lang.Thread.sleep

class HomePage : AppCompatActivity() {
    lateinit var editProfileHP:ImageView
    lateinit var tasksIcon:BottomNavigationItemView
    lateinit var settingsIcon:BottomNavigationItemView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        editProfileHP=findViewById<ImageView>(R.id.editProfileFromHomePage)
       editProfileHP.setOnClickListener{

            val intent = Intent(this,editProfile::class.java)
            startActivity(intent)
        }

        tasksIcon=findViewById<BottomNavigationItemView>(R.id.tasks)
        tasksIcon.setOnClickListener{

            val intent = Intent(this, newTask::class.java)
            startActivity(intent)

        }

        settingsIcon=findViewById<BottomNavigationItemView>(R.id.settings)
        settingsIcon.setOnClickListener{

            val intent = Intent(this, settings::class.java)
            startActivity(intent)

        }


    }
}