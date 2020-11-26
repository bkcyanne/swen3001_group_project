package com.example.killmenow

import android.app.AlertDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.taskdatabase.Task
import com.example.taskdatabase.TaskViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import java.text.SimpleDateFormat
import java.util.*


class AddFragment : Fragment() {

    private lateinit var mTaskViewModel: TaskViewModel
    val startCalendar = Calendar.getInstance()
    val endCalendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        //DATE PICKER
        view.datePickerBegin.init(startCalendar.get(Calendar.YEAR),startCalendar.get(Calendar.MONTH),startCalendar.get(Calendar.DAY_OF_MONTH)){
                view,year,month,day ->
            val month=month+1
            val message ="$day/$month/$year Selected"
           // Toast.makeText(requireContext(),message, Toast.LENGTH_SHORT).show()
        }

        view.datePickerEnd.init(endCalendar.get(Calendar.YEAR),endCalendar.get(Calendar.MONTH),endCalendar.get(Calendar.DAY_OF_MONTH)){
                view,year,month,day ->
            val month=month+1
            val message ="$day/$month/$year Selected"
           // Toast.makeText(requireContext(),message, Toast.LENGTH_SHORT).show()
        }
        //END DATE PICKER

        //TIME PICKER
        view.StartHourTime.setOnClickListener {

            //val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener{
                    timePicker,hour,minute ->
                startCalendar.set(Calendar.HOUR_OF_DAY,hour)
                startCalendar.set(Calendar.MINUTE,minute)
                view.StartHourTime.text = SimpleDateFormat("HH:mm").format(startCalendar.time)

            }
            TimePickerDialog(requireContext(),timeSetListener,startCalendar.get(Calendar.HOUR_OF_DAY),(Calendar.MINUTE),false).show()
        }


        view.EndHourTime.setOnClickListener {

            //val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener{
                    timePicker,hour,minute ->
                endCalendar.set(Calendar.HOUR_OF_DAY,hour)
                endCalendar.set(Calendar.MINUTE,minute)
                view.EndHourTime.text = SimpleDateFormat("HH:mm").format(endCalendar.time)

            }
            TimePickerDialog(requireContext(),timeSetListener,endCalendar.get(Calendar.HOUR_OF_DAY),(Calendar.MINUTE),false).show()
        }
        //END TIME PICKER
        view.addTaskButton.setOnClickListener{
            insertTaskToDatabase()
        }

        view.cancel_button.setOnClickListener{
            val builder = AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Yes"){_,_->
                findNavController().navigate(R.id.action_addFragment_to_listFragment)
            }
            builder.setNegativeButton("No") {_,_->

            }
            builder.setTitle("Cancel")
            builder.setMessage("Are you sure you want to cancel?")
            builder.create().show()
        }

        return view
    }

    private fun insertTaskToDatabase() {
        val name = TaskName.text.toString()
        val description = TaskDescription.text.toString()

        val startYear = startCalendar.get(Calendar.YEAR)
        val startMonth = startCalendar.get(Calendar.MONTH)
        val startDay = startCalendar.get(Calendar.DAY_OF_MONTH)
        val startTimeHour = startCalendar.get(Calendar.HOUR)
        val startTimeMinute = startCalendar.get(Calendar.MINUTE)

        val endYear = endCalendar.get(Calendar.YEAR)
        val endMonth = endCalendar.get(Calendar.MONTH)
        val endDay = endCalendar.get(Calendar.DAY_OF_MONTH)
        val endTimeHour = startCalendar.get(Calendar.HOUR)
        val endTimeMinute = endCalendar.get(Calendar.MINUTE)

        if(inputCheck(name)==true) {
            val task = Task(0,name,description,startYear,startMonth,startDay,startTimeHour,startTimeMinute,endYear,endMonth,endDay,endTimeHour,endTimeMinute,0)
            mTaskViewModel.addTask(task)
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please input a task name", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(name: String): Boolean{
        return !(TextUtils.isEmpty(name))
    }

}