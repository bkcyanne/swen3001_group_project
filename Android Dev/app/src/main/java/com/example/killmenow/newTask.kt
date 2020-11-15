package com.example.killmenow

import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.widget.*
import androidx.core.text.toSpannable
import kotlinx.android.synthetic.main.activity_new_task.*
import java.lang.Thread.sleep

class newTask : AppCompatActivity() {
    lateinit var ctTaskName: String
    lateinit var ctTaskDescription:String
    lateinit var ctTaskNameInput: TextView
    lateinit var ctTaskDescriptionInput:TextView
    lateinit var startTime:TextView
    lateinit var endTime:TextView
    var startTimeHour: Int = 0
    var startTimeMinute: Int = 0
    var endTimeHour: Int = 0
    var endTimeHMinute: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)
        ctTaskNameInput=findViewById<EditText>(R.id.TaskName)
        ctTaskDescriptionInput=findViewById<EditText>(R.id.TaskDescription)
        startTime=findViewById<TextView>(R.id.StartHourTime)
        endTime=findViewById<TextView>(R.id.EndHourTime)



        val addTaskButtonClicked =findViewById<Button>(R.id.addTaskButton)
        addTaskButtonClicked.setOnClickListener {
            ctTaskName=ctTaskNameInput.text.toString()
            ctTaskDescription=ctTaskDescriptionInput.text.toString()

            val intent= Intent(this,viewAllTasks::class.java)
            startActivity(intent)



        }
    }
}