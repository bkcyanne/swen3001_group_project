package com.example.killmenow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.Thread.sleep

class newTask : AppCompatActivity() {
    lateinit var ctTaskName: String
    lateinit var ctTaskDescription:String
    lateinit var ctTaskNameInput: TextView
    lateinit var ctTaskDescriptionInput:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)
       val addTask =findViewById<Button>(R.id.addTaskButton)
        addTask.setOnClickListener {
            Toast.makeText(this,"Task Added",Toast.LENGTH_SHORT).show()
                sleep(3000)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

        }

    }
}