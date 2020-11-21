package com.example.database

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText


import androidx.recyclerview.widget.RecyclerView
import com.example.killmenow.R
import kotlinx.android.synthetic.main.activity_acc_creation_page.view.*
import kotlinx.android.synthetic.main.activity_edit_profile.view.*
import kotlinx.android.synthetic.main.activity_edit_profile.view.fullName
import kotlinx.android.synthetic.main.custom_row.view.*

class UserAdapter: RecyclerView.Adapter<UserAdapter.MyViewHolder>(){

    private var userList = emptyList<User>()

    class MyViewHolder(itemView :View ):RecyclerView.ViewHolder(itemView) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.MyViewHolder {
        return MyViewHolder((LayoutInflater.from(parent.context).inflate(R.layout.activity_acc_creation_page,parent,false)))
    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        val currentItem = userList[position]





    }

    override fun getItemCount(): Int {
       return userList.size
    }
}