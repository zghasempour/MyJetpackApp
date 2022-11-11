package com.example.myfirstjetpackapp.data

import androidx.room.TypeConverters
import java.util.Date

class DateConverter {

    @TypeConverters
    fun fromTimestamp(value: Long): Date{
        return Date(value)
    }

    @TypeConverters
    fun dateToTimestamp(date: Date): Long{
        return date.time
    }
}