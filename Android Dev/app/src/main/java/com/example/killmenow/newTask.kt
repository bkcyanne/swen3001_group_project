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
import java.text.Format
import java.text.SimpleDateFormat
import java.util.*

class newTask : AppCompatActivity() {
    lateinit var ctTaskName: String
    lateinit var ctTaskDescription:String
    lateinit var ctTaskNameInput: TextView
    lateinit var ctTaskDescriptionInput:TextView
    lateinit var startTime:TextView
    lateinit var endTime:TextView
    lateinit var datePick:DatePicker
    var startTimeHour: Int = 0
    var startTimeMinute: Int = 0
    var endTimeHour: Int = 0
    var endTimeHMinute: Int = 0
    lateinit var timeSet:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)
        ctTaskNameInput=findViewById<EditText>(R.id.TaskName)
        ctTaskDescriptionInput=findViewById<EditText>(R.id.TaskDescription)
        startTime=findViewById<TextView>(R.id.StartHourTime)
        endTime=findViewById<TextView>(R.id.EndHourTime)

        ctTaskName=ctTaskNameInput.text.toString()

        startTime.setOnClickListener {

            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener{
                timePicker,hour,minute ->
                cal.set(Calendar.HOUR_OF_DAY,hour)
                cal.set(Calendar.MINUTE,minute)
                startTime.text = SimpleDateFormat("HH:mm").format(cal.time)

            }
            TimePickerDialog(this,timeSetListener,cal.get(Calendar.HOUR_OF_DAY),(Calendar.MINUTE),false).show()
        }


        endTime.setOnClickListener {

            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener{
                    timePicker,hour,minute ->
                cal.set(Calendar.HOUR_OF_DAY,hour)
                cal.set(Calendar.MINUTE,minute)
                endTime.text = SimpleDateFormat("HH:mm").format(cal.time)

            }
            TimePickerDialog(this,timeSetListener,cal.get(Calendar.HOUR_OF_DAY),(Calendar.MINUTE),false).show()
        }


            val datePick=findViewById<DatePicker>(R.id.datePicker1)
            val today= Calendar.getInstance()
        datePick.init(today.get(Calendar.YEAR),today.get(Calendar.MONTH),today.get(Calendar.DAY_OF_MONTH)){


            view,year,month,day ->
            val month=month+1
            val message ="$day/$month/$year Selected"
            Toast.makeText(this,message,Toast.LENGTH_SHORT).show()


        }


        val addTaskButtonClicked =findViewById<Button>(R.id.addTaskButton)
        addTaskButtonClicked.setOnClickListener {
            ctTaskName=ctTaskNameInput.text.toString()
            ctTaskDescription=ctTaskDescriptionInput.text.toString()

            Toast.makeText(this, ctTaskName+"Added Successfully",Toast.LENGTH_SHORT).show()
            val intent= Intent(this,viewAllTasks::class.java)
            startActivity(intent)



        }
    }
}