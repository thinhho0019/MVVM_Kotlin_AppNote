package vn.edu.stu.noteappmvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import vn.edu.stu.noteappmvvm.database.noteDB
import vn.edu.stu.noteappmvvm.databinding.ActivityMainBinding
import vn.edu.stu.noteappmvvm.repository.noteRepository
import vn.edu.stu.noteappmvvm.viewmodel.NoteViewModel
import vn.edu.stu.noteappmvvm.viewmodel.NoteViewModelProviderFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    lateinit var noteViewModel :NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        setUpViewModel()
    }
    private fun setUpViewModel(){
        val noteRes = noteRepository(noteDB(this))
        val viewModelProviderFactory = NoteViewModelProviderFactory(application,noteRes)
        noteViewModel = ViewModelProvider(this,viewModelProviderFactory).get(NoteViewModel::class.java)
    }
}