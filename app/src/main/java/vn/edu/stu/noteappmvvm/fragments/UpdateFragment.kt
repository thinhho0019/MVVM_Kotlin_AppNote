package vn.edu.stu.noteappmvvm.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import vn.edu.stu.noteappmvvm.MainActivity
import vn.edu.stu.noteappmvvm.R
import vn.edu.stu.noteappmvvm.databinding.FragmentUpdateBinding
import vn.edu.stu.noteappmvvm.model.Notes
import vn.edu.stu.noteappmvvm.toast
import vn.edu.stu.noteappmvvm.viewmodel.NoteViewModel


class UpdateFragment : Fragment() {
    private var _binding : FragmentUpdateBinding?=null
    private val binding get() = _binding!!
    private val args:UpdateFragmentArgs by navArgs()
    private lateinit var currentNote:Notes
    private lateinit var notemodelView : NoteViewModel
    private lateinit var mView:View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notemodelView = (activity as MainActivity).noteViewModel
        mView = view
        currentNote = args.note!!
        binding.editTitle.setText(currentNote.title)
        binding.editBody.setText(currentNote.noteBody)
        binding.floatingActionButton3.setOnClickListener(View.OnClickListener {

            if(binding.editTitle.text.toString().isNotEmpty()){
                val title = binding.editTitle.text.toString()
                val body =binding.editBody.text.toString()
                val note = Notes(currentNote.id, title,body)
                notemodelView.addNote(note)
                Snackbar.make(view,"Thêm thành công",Snackbar.LENGTH_SHORT).show()
                view.findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
            }else{
                activity?.toast("nhập đầy đủ để cập nhật")
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.update_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.delete -> {deleteNote(mView)}
        }
        return super.onOptionsItemSelected(item)
    }
    private fun deleteNote(vi:View){
        AlertDialog.Builder(activity).apply {
            setTitle("Xóa ghi chú")
            setMessage("Bạn thật sự muốn xóa?")
            setPositiveButton("Xóa"){_,_ ->
                notemodelView.deleteNote(currentNote)
                Snackbar.make(vi,"xóa thành công",Snackbar.LENGTH_SHORT).show()
                val direct  = UpdateFragmentDirections.actionUpdateFragmentToHomeFragment()
                vi?.findNavController()?.navigate(
                    direct
                )
            }
            setNegativeButton("Hủy",null,)
        }.create().show()

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


}