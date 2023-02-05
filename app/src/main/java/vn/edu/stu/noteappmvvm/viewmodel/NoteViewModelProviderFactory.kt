package vn.edu.stu.noteappmvvm.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import vn.edu.stu.noteappmvvm.repository.noteRepository

class NoteViewModelProviderFactory(val app:Application,private val res :noteRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(app,res) as T
    }
}