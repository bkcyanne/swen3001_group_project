package com.example.database

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import androidx.recyclerview.widget.RecyclerView
import com.example.killmenow.R
import kotlinx.android.synthetic.main.activity_acc_creation_page.view.*
import kotlinx.android.synthetic.main.activity_edit_profile.view.*
import kotlinx.android.synthetic.main.activity_edit_profile.view.fullName
import kotlinx.android.synthetic.main.custom_row.view.*

/*class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>(){

    private var userList = emptyList<User>()

    class MyViewHolder(itemView :View ):RecyclerView.ViewHolder(itemView) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.MyViewHolder {
        return MyViewHolder((LayoutInflater.from(parent.context).inflate(R.layout.activity_acc_creation_page,parent,false)))
    }

    override fun onBindViewHolder(holder: ListAdapter.MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.idText.text=currentItem.toString()
        holder.itemView.customFullName.text=currentItem.firstName
        holder.itemView.customEmail.text=currentItem.toString()
        holder.itemView.customPassword.text=currentItem.toString()



    }

    override fun getItemCount(): Int {
       return userList.size
    }
}*/