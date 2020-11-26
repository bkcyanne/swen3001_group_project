package com.example.taskdatabase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.killmenow.ListFragmentDirections
import com.example.killmenow.R
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var taskList = emptyList<Task>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false));
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun formatTime(time: Int): String {
        if(time < 10) {
            return "0"+time.toString()
        } else {
            return time.toString()
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = taskList[position]
        holder.itemView.textName.text = currentItem.name.toString()
        holder.itemView.textDescription.text = currentItem.description.toString()
        holder.itemView.textDate.text = currentItem.endDay.toString()+"/"+currentItem.endTimeMonth.toString()+"/"+currentItem.endTimeYear.toString()+"     "+currentItem.endTimeHour.toString()+":"+currentItem.endTimeMinute.toString()

        holder.itemView.custom_row.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

        //MARK TASKS AS COMPLETE OR INCOMPLETE WHEN CHECK BOX IS TICKED
        //DID NOT WORK
        /*
        holder.itemView.status.setOnClickListener{
            var status = 0

            if(holder.itemView.status.isChecked) {
                mTaskViewModel.completeTask(currentItem.id)
            } else {
                mTaskViewModel.incompleteTask(currentItem.id)
            }
        }*/
    }

    fun setData(task: List<Task>) {
        this.taskList = task
        notifyDataSetChanged()
    }
}