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
import androidx.lifecycle.ViewModelProvider
import com.example.taskdatabase.Task
import com.example.taskdatabase.TaskViewModel
import kotlinx.android.synthetic.main.activity_new_task.*
import java.lang.Thread.sleep
import java.text.Format
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.*

class EditTask : AppCompatActivity() {
    lateinit var ctTaskName: String
    lateinit var ctTaskDescription:String
    lateinit var ctTaskNameInput: TextView
    lateinit var ctTaskDescriptionInput:TextView
    lateinit var startTime:TextView
    lateinit var endTime:TextView
    lateinit var datePick:DatePicker
    var endTimeHour: Int = 0
    var endTimeHMinute: Int = 0
    lateinit var timeSet:String

    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        ctTaskNameInput=findViewById<EditText>(R.id.TaskName)
        ctTaskDescriptionInput=findViewById<EditText>(R.id.TaskDescription)
        startTime=findViewById<TextView>(R.id.StartHourTime)
        endTime=findViewById<TextView>(R.id.EndHourTime)

        ctTaskName=ctTaskNameInput.text.toString()


        val startCalendar = Calendar.getInstance()
        val endCalendar = Calendar.getInstance()

        startTime.setOnClickListener {

            //val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener{
                    timePicker,hour,minute ->
                startCalendar.set(Calendar.HOUR_OF_DAY,hour)
                startCalendar.set(Calendar.MINUTE,minute)
                startTime.text = SimpleDateFormat("HH:mm").format(startCalendar.time)

            }
            TimePickerDialog(this,timeSetListener,startCalendar.get(Calendar.HOUR_OF_DAY),(Calendar.MINUTE),false).show()
        }


        endTime.setOnClickListener {

            //val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener{
                    timePicker,hour,minute ->
                endCalendar.set(Calendar.HOUR_OF_DAY,hour)
                endCalendar.set(Calendar.MINUTE,minute)
                endTime.text = SimpleDateFormat("HH:mm").format(endCalendar.time)

            }
            TimePickerDialog(this,timeSetListener,endCalendar.get(Calendar.HOUR_OF_DAY),(Calendar.MINUTE),false).show()
        }


        val datePick=findViewById<DatePicker>(R.id.datePicker1)
        val today= Calendar.getInstance()
        datePick.init(today.get(Calendar.YEAR),today.get(Calendar.MONTH),today.get(Calendar.DAY_OF_MONTH)){


                view,year,month,day ->
            val month=month+1
            val message ="$day/$month/$year Selected"
            Toast.makeText(this,message,Toast.LENGTH_SHORT).show()


        }
/*
        /*
        Check if input fields are blank before entering into database
         */
        fun inputCheck(taskName: String, descr: String): Int {
            if(taskName.length > 0 && descr.length > 0) {
                return 1
            } else {
                return 0
            }
        }

        var startTimeHour: Int
        var startTimeMinute: Int
        var startMonth: Int
        var startDay: Int
        var startAmPm: Int

        var endTimeHour: Int
        var endTimeMinute: Int
        var endMonth: Int
        var endDay: Int
        var endAmPm: Int

        val addTaskButtonClicked =findViewById<Button>(R.id.addTaskButton)
        addTaskButtonClicked.setOnClickListener {

            /*
            Get data from calendar to create Task
             */
            ctTaskName=ctTaskNameInput.text.toString()
            ctTaskDescription=ctTaskDescriptionInput.text.toString()

            //Start Time
            startTimeHour = startCalendar.get(HOUR)
            startTimeMinute = startCalendar.get(MINUTE)
            startMonth = startCalendar.get(MONTH)
            startDay = startCalendar.get(DAY_OF_WEEK)
            startAmPm = startCalendar.get(AM_PM)

            //End Time
            endTimeHour = endCalendar.get(HOUR)
            endTimeMinute = endCalendar.get(MINUTE)
            endMonth = endCalendar.get(MONTH)
            endDay = endCalendar.get(DAY_OF_WEEK)
            endAmPm = endCalendar.get(AM_PM)


            /*
            INPUT TASK INTO TASK DATABASE
             */
            if(inputCheck(ctTaskName, ctTaskDescription) == 1) {
                val task = Task(0,
                    ctTaskName,
                    ctTaskDescription,
                    startMonth,
                    startDay,
                    startTimeHour,
                    startTimeMinute,
                    startAmPm,
                    endMonth,
                    endDay,
                    endTimeHour,
                    endTimeMinute,
                    endAmPm
                )

                taskViewModel.addTask(task)

                Toast.makeText(this, ctTaskName+"Added Successfully",Toast.LENGTH_SHORT).show()
                val intent= Intent(this,viewAllTasks::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Fill in all fields",Toast.LENGTH_SHORT).show()
            }
        }


*/
    }


}