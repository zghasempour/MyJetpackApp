package com.example.myfirstjetpackapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfirstjetpackapp.data.NoteEntity
import com.example.myfirstjetpackapp.data.SampleDateProvider

class MainViewModel : ViewModel() {
    val notesList = MutableLiveData<List<NoteEntity>>()

    init {
        notesList.value = SampleDateProvider().getNotes()
    }

}