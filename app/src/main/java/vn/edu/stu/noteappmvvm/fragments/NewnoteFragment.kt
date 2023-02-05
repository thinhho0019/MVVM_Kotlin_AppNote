package vn.edu.stu.noteappmvvm.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import vn.edu.stu.noteappmvvm.MainActivity
import vn.edu.stu.noteappmvvm.R
import vn.edu.stu.noteappmvvm.databinding.FragmentNewnoteBinding
import vn.edu.stu.noteappmvvm.model.Notes
import vn.edu.stu.noteappmvvm.toast
import vn.edu.stu.noteappmvvm.viewmodel.NoteViewModel


class NewnoteFragment : Fragment() {
    private var _binding:FragmentNewnoteBinding?=null
    private val binding get() = _binding!!
    private lateinit var noteViewModel:NoteViewModel
    private lateinit var mView:View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }
    private fun saveNote(view:View){
        val noteTitle =binding.title.text.toString().trim()
        val noteBody = binding.title.text.toString().trim()
        if(noteTitle.isNotEmpty()){
            val note = Notes(0,noteTitle,noteBody)
            noteViewModel.addNote(note)
            Snackbar.make(view,"đã thêm note thành công!",Snackbar.LENGTH_SHORT).show()
            view.findNavController().navigate(
                R.id.action_newnoteFragment_to_homeFragment
            )
        }else{
            activity?.toast("vui lòng nhập tiêu đề cho ghi chú")
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.save->{
                saveNote(mView)
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel = (activity as MainActivity).noteViewModel
        mView =view
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewnoteBinding.inflate(layoutInflater,container,false);
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.new_node_menu,menu);
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding =null
    }
}