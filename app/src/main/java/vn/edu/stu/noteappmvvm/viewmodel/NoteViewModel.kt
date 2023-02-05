package vn.edu.stu.noteappmvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import vn.edu.stu.noteappmvvm.model.Notes
import vn.edu.stu.noteappmvvm.repository.noteRepository

class NoteViewModel(
    application: Application,
    private val res:noteRepository,
): AndroidViewModel(application) {
    fun addNote(note:Notes)= viewModelScope.launch {
        res.addNote(note)
    }
    fun updateNote(note:Notes) = viewModelScope.launch {
        res.updateNote(note)
    }

    fun deleteNote(note:Notes) = viewModelScope.launch {
        res.deleteNote(note)
    }

    fun getAllNote() = res.getAllNote()
}