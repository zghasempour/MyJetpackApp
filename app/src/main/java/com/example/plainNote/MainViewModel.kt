package com.example.plainNote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.plainNote.data.AppDatabase
import com.example.plainNote.data.NoteEntity
import com.example.plainNote.data.SampleDataProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(app: Application) : AndroidViewModel(app) {
    private val database = AppDatabase.getInstance(app)
    val notesList = database?.noteDao()?.getAll()

    fun addSampleNotes() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val sampleNotes = SampleDataProvider.getNotes()
                database?.noteDao()?.insertAll(sampleNotes)
            }
        }
    }

    fun deleteNotes(selectedNotes: List<NoteEntity>) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                database?.noteDao()?.delete(selectedNotes)
            }
        }
    }

    fun deleteAllNotes() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                database?.noteDao()?.deleteAll()
            }
        }
    }
}