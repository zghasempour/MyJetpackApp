package com.example.plainNote

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.plainNote.data.AppDatabase
import com.example.plainNote.data.NoteDao
import com.example.plainNote.data.NoteEntity
import com.example.plainNote.data.SampleDataProvider
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before


@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    private lateinit var dao: NoteDao
    private lateinit var database: AppDatabase

    @Before
    fun createDb() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(appContext, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        dao = database.noteDao()!!
    }

    @Test
    fun createNotes() {
        dao.insertAll(SampleDataProvider.getNotes())
        val count = dao.getCount()
        assertEquals(count, SampleDataProvider.getNotes().size)
    }

    @Test
    fun addNote() {
        val note = NoteEntity()
        note.text = "Yes"
        dao.insertNote(note)

        val savedNote = dao.getNoteById(1)
        assertEquals(savedNote.id ?: 0, 1)
    }

    @After
    fun closeDb() {
        database.close()
    }
}