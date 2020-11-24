package com.example.killmenow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.killmenow.R
import com.example.taskdatabase.ListAdapter
import com.example.taskdatabase.TaskViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*


class ListFragment : Fragment() {

    private lateinit var mTaskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)


        //Recycler View
        val adapter = ListAdapter()
        val recyclerView = view.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        mTaskViewModel.readAllData.observe(viewLifecycleOwner, Observer {task ->
            adapter.setData(task)

        })

        view.floatingActionButton2.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }


        return view
    }
}