package vn.edu.stu.noteappmvvm.repository

import vn.edu.stu.noteappmvvm.database.noteDB
import vn.edu.stu.noteappmvvm.model.Notes

class noteRepository(private val db:noteDB) {
    suspend fun addNote(note:Notes) = db.getNoteDao().addNote(note)
    suspend fun updateNote(note: Notes) = db.getNoteDao().updateNote(note)
    suspend fun  deleteNote(note:Notes) = db.getNoteDao().deleteNote(note)
    fun getAllNote() = db.getNoteDao().getAllNotes()
}