package com.example.killmenow

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskdatabase.ListAdapter
import com.example.taskdatabase.TaskViewModel
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_view_all_tasks.*
import kotlinx.android.synthetic.main.activity_view_all_tasks.view.*
import java.lang.Thread.sleep

class viewAllTasks : AppCompatActivity() {

    private lateinit var mTaskViewModel: TaskViewModel
    lateinit var tasksIcon: BottomNavigationItemView
    lateinit var settingsIcon: BottomNavigationItemView
    lateinit var homeIcon:BottomNavigationItemView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_all_tasks)

        tasksIcon = findViewById<BottomNavigationItemView>(R.id.tasks)
        tasksIcon.setOnClickListener {

            val intent = Intent(this, viewAllTasks::class.java)
            startActivity(intent)


        }

        settingsIcon = findViewById<BottomNavigationItemView>(R.id.settings)
        settingsIcon.setOnClickListener {

            val intent = Intent(this, settings::class.java)
            startActivity(intent)


        }
        homeIcon=findViewById<BottomNavigationItemView>(R.id.homePage)
        homeIcon.setOnClickListener {

            val intent = Intent(this, WeatherPortion::class.java)
            startActivity(intent)


        }



        // Recyclerview
        val adapter = ListAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerviewtasks)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // UserViewModel
        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        mTaskViewModel.readAllData.observe(this, Observer {user->
            adapter.setData(user)
        })

        val addTask = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        addTask.setOnClickListener{

            floatingActionButton.setColorFilter(Color.CYAN)
            sleep(100)

            val intent = Intent(this, newTask::class.java)
            startActivity(intent)



        }
    }

}
