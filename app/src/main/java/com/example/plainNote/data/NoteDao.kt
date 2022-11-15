package com.example.plainNote.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note : NoteEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(notes : List<NoteEntity>)

    @Query("SELECT * FROM notes ORDER BY date ASC")
    fun getAll() : LiveData<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE id = :id")
    fun getNoteById(id: Int) : NoteEntity

    @Query("SELECT COUNT(*) FROM notes")
    fun getCount(): Int

    @Delete
    fun delete(selectedNotes: List<NoteEntity>) : Int

    @Query( "DELETE FROM notes " )
    fun deleteAll():Int

    @Delete
    fun deleteNote(noteEntity: NoteEntity)
}