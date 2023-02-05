package vn.edu.stu.noteappmvvm.database

import androidx.lifecycle.LiveData
import androidx.room.*
import vn.edu.stu.noteappmvvm.model.Notes

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note:Notes)

    @Update
    suspend fun updateNote(note: Notes)

    @Delete
    suspend fun deleteNote(note: Notes)

    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAllNotes():LiveData<List<Notes>>
}