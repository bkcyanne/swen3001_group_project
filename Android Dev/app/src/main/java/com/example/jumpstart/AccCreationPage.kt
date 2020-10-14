package com.example.jumpstart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.lang.Thread.sleep

class AccCreationPage : AppCompatActivity() {
    lateinit var accName: String;lateinit var accEmail: String;lateinit var accPassword: String;lateinit var accPasswordConf:String

    lateinit var accNameInput: EditText;lateinit var accEmailInput: EditText;lateinit var accPasswordInput:EditText;lateinit var accPasswordConfInput:EditText

    var emailPattern = Regex("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acc_creation_page)

        accNameInput= findViewById(R.id.cpName)
        accEmailInput= findViewById(R.id.cpEmail)
        accPasswordInput= findViewById(R.id.cpPassword)
        accPasswordConfInput= findViewById(R.id.cpPasswordConf)


        val createButton = findViewById<Button>(R.id.accCreationButton)
        createButton.setOnClickListener {
            accName = accNameInput.text.toString();
            accEmail = accEmailInput.text.toString()
            accPassword= accPasswordInput.text.toString()
            accPasswordConf=accPasswordConfInput.text.toString()

            if(!emailPattern.containsMatchIn(accEmail)){

                Toast.makeText(this,"Please enter valid Email address",Toast.LENGTH_SHORT).show()
            }else if(accPassword.length<=5){
                Toast.makeText(this,"Password must be 6 digits or more",Toast.LENGTH_SHORT).show()

            }else if(accPassword!=accPasswordConf){
                Toast.makeText(this,"Passwords do not match",Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(this,"Account Successfully Created",Toast.LENGTH_SHORT).show()
                sleep(3000)
                val intent = Intent(this, CreateNewTask::class.java)

                startActivity(intent)
            }

        }
    }
}