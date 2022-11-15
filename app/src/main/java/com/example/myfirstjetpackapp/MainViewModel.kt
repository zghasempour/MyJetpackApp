package com.example.myfirstjetpackapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirstjetpackapp.data.AppDatabase
import com.example.myfirstjetpackapp.data.NoteEntity
import com.example.myfirstjetpackapp.data.SampleDataProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(app: Application) : AndroidViewModel(app) {
    private val database = AppDatabase.getInstance(app)
    val notesList = database?.noteDao()?.getAll()

    fun addSampleNotes(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val sampleNotes = SampleDataProvider.getNotes()
                database?.noteDao()?.insertAll(sampleNotes)
            }
        }
    }
}