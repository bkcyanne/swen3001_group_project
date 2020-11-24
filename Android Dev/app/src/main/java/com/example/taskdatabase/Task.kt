package com.example.taskdatabase

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "start_year")
    val startTimeYear: Int,

    @ColumnInfo(name = "start_month")
    val startTimeMonth: Int,

    @ColumnInfo(name = "start_day")
    val startDay: Int,

    @ColumnInfo(name = "start_hour")
    val startTimeHour: Int,

    @ColumnInfo(name = "start_minute")
    val startTimeMinute: Int,

    @ColumnInfo(name = "end_year")
    val endTimeYear: Int,

    @ColumnInfo(name = "end_month")
    val endTimeMonth: Int,

    @ColumnInfo(name = "end_day")
    val endDay: Int,

    @ColumnInfo(name = "end_hour")
    val endTimeHour: Int,

    @ColumnInfo(name = "end_minute")
    val endTimeMinute: Int,

    @ColumnInfo(name = "status")
    val status: Int
): Parcelable