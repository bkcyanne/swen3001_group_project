package com.example.killmenow

import android.app.AlertDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.taskdatabase.Task
import com.example.taskdatabase.TaskViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.TaskName
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import java.text.SimpleDateFormat
import java.time.Month
import java.util.*


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private val startCalendar = Calendar.getInstance()
    private val endCalendar = Calendar.getInstance()
    private lateinit var mTaskViewModel: TaskViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        //GET AND SET START CALENDAR VARIABLES
        val starYear = args.currentTask.startTimeYear
        val startMonth = args.currentTask.startTimeMonth
        val startDay = args.currentTask.startDay
        val startHour = args.currentTask.startTimeHour
        val startMinute = args.currentTask.startTimeMinute

        startCalendar.set(starYear, startMonth, startDay, startHour, startMinute)


        //GET AND SET END CALENDAR VARIABLES
        val endYear = args.currentTask.endTimeYear
        val endMonth = args.currentTask.endTimeMonth
        val endDay = args.currentTask.endDay
        val endHour = args.currentTask.endTimeHour
        val endMinute = args.currentTask.endTimeMinute

        endCalendar.set(endYear, endMonth, endDay, endHour, endMinute)

        view.UpdateTaskName.setText(args.currentTask.name)
        view.UpdateTaskDescription.setText(args.currentTask.description)

        /*
        view.UpdatedatePickerBegin.init(startCalendar.get(Calendar.YEAR),startCalendar.get(Calendar.MONTH),startCalendar.get(Calendar.DAY_OF_MONTH)){
                view,year,month,day ->
            val month=month+1
            val message ="$day/$month/$year Selected"
          //  Toast.makeText(requireContext(),message, Toast.LENGTH_SHORT).show()
        }
         */

        view.UpdatedatePickerEnd.init(endCalendar.get(Calendar.YEAR),endCalendar.get(Calendar.MONTH),endCalendar.get(Calendar.DAY_OF_MONTH)){
                view,year,month,day ->
            val month=month+1
            val message ="$day/$month/$year Selected"
           // Toast.makeText(requireContext(),message, Toast.LENGTH_SHORT).show()
        }

        //TIME PICKER
        view.UpdateStartHourTime.setOnClickListener {
            val timeSetListener = TimePickerDialog.OnTimeSetListener{
                    timePicker,hour,minute ->
                startCalendar.set(Calendar.HOUR_OF_DAY,hour)
                startCalendar.set(Calendar.MINUTE,minute)
                view.StartHourTime.text = SimpleDateFormat("HH:mm").format(startCalendar.time)
            }
            TimePickerDialog(requireContext(),timeSetListener,startCalendar.get(Calendar.HOUR_OF_DAY),(Calendar.MINUTE),false).show()
        }


        view.UpdateEndHourTime.setOnClickListener {
            val timeSetListener = TimePickerDialog.OnTimeSetListener{
                    timePicker,hour,minute ->
                endCalendar.set(Calendar.HOUR_OF_DAY,hour)
                endCalendar.set(Calendar.MINUTE,minute)
                view.EndHourTime.text = SimpleDateFormat("HH:mm").format(endCalendar.time)
            }
            TimePickerDialog(requireContext(),timeSetListener,endCalendar.get(Calendar.HOUR_OF_DAY),(Calendar.MINUTE),false).show()
        }

        var status = args.currentTask.status
        view.Complete.setOnClickListener{
            if(view.Complete.isChecked) {
                status = 1
            }
            else {
                status = 0
            }
        }

        view.updateTaskButton.setOnClickListener{
            updateTask(status)
        }

        /*
        Listener for delete task button
         */
        view.deleteTask.setOnClickListener{
            val id = args.currentTask.id
            val name = args.currentTask.name
            val description = args.currentTask.description

            val startYear = args.currentTask.startTimeYear
            val startMonth = args.currentTask.startTimeMonth
            val startDay = args.currentTask.startDay
            val startTimeHour = args.currentTask.startTimeHour
            val startTimeMinute = args.currentTask.startTimeMinute

            val endYear = args.currentTask.endTimeYear
            val endMonth = args.currentTask.endTimeMonth
            val endDay = args.currentTask.endDay
            val endTimeHour = args.currentTask.endTimeHour
            val endTimeMinute = args.currentTask.endTimeMinute
            val status = args.currentTask.status
            deleteTask(id, name, description, startYear, startMonth, startDay, startTimeHour, startTimeMinute, endYear, endMonth, endDay, endTimeHour, endTimeMinute, status)
        }
        return view
    }


    private fun deleteTask(id: Int, name: String, description: String, startYear: Int, startMonth: Int, startDay: Int, startTimeHour: Int, startTimeMinute: Int, endYear: Int, endMonth: Int, endDay: Int, endTimeHour: Int, endTimeMinute: Int, status: Int) {
        /*
        Alert dialogue for deleting a task
         */
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_->
            val deletedTask = Task(id,name,description,startYear,startMonth,startDay,startTimeHour,startTimeMinute,endYear,endMonth,endDay,endTimeHour,endTimeMinute,status)
            mTaskViewModel.deleteTask(deletedTask)
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") {_,_-> }
        builder.setTitle("Delete ${args.currentTask.name}?")
        builder.setMessage("Are you sure you want to delete ${args.currentTask.name}?")
        builder.create().show()
    }


    private fun updateTask(status: Int) {
        val name = UpdateTaskName.text.toString()
        val description = UpdateTaskDescription.text.toString()

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
            val updateTask = Task(args.currentTask.id,name,description,startYear,startMonth,startDay,startTimeHour,startTimeMinute,endYear,endMonth,endDay,endTimeHour,endTimeMinute,status)
            mTaskViewModel.updateTask(updateTask)
            Toast.makeText(requireContext(), "Successfully updated", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please input a task name", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(name: String): Boolean{
        return !(TextUtils.isEmpty(name))
    }
}