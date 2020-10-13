package com.example.jumpstart

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class CreateNewTask : AppCompatActivity() {
    lateinit var taskName: String; lateinit var taskDesc:String
    lateinit var taskNameInput: EditText; lateinit var taskDescInput:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        taskNameInput=findViewById(R.id.etcntTaskName)
        taskDescInput=findViewById(R.id.etcntTaskDescription)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_task)

        val cntImageBackButton = findViewById<ImageView>(R.id.cntBackButton)
        cntImageBackButton.setOnClickListener {
            val intent = Intent(this, AllTasks::class.java)

            startActivity(intent)
        }

        val cntAddTaskButton = findViewById<Button>(R.id.cntAddTaskButton)
        cntImageBackButton.setOnClickListener {
            taskName=taskNameInput.text.toString()
            taskDesc= taskDescInput.text.toString()


            val intent = Intent(this, AllTasks::class.java)

            startActivity(intent)
        }
    }
}