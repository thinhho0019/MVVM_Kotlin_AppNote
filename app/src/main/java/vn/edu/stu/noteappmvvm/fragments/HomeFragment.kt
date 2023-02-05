package vn.edu.stu.noteappmvvm.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import vn.edu.stu.noteappmvvm.MainActivity
import vn.edu.stu.noteappmvvm.R
import vn.edu.stu.noteappmvvm.adapter.NoteApdater
import vn.edu.stu.noteappmvvm.databinding.FragmentHomeBinding
import vn.edu.stu.noteappmvvm.model.Notes
import vn.edu.stu.noteappmvvm.viewmodel.NoteViewModel


class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding:FragmentHomeBinding?=null
    private val binding  get() =  _binding!!
    private lateinit var noteViewModel:NoteViewModel
    private lateinit var noteAdap:NoteApdater
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater,container,false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel = (activity as MainActivity).noteViewModel
        setUpRecyclerView()
        binding.btnNewNode.setOnClickListener { v->
            v.findNavController().navigate(R.id.action_homeFragment_to_newnoteFragment)
         }
    }
    private fun setUpRecyclerView(){
         noteAdap = NoteApdater()

        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )
            setHasFixedSize(true)
            adapter=noteAdap

        }
        activity?.let {
            noteViewModel.getAllNote().observe(viewLifecycleOwner,{ note ->
                noteAdap.differ.submitList(note)
                updateUI(note)
            })
        }
    }
    private fun updateUI(note:List<Notes>){
        if (note.isNotEmpty()){
            binding.recyclerView.visibility = View.VISIBLE
            binding.tvShowNoteAvaiable.visibility =View.GONE
        }else{
            binding.recyclerView.visibility = View.GONE
            binding.tvShowNoteAvaiable.visibility =View.VISIBLE
        }
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_menu,menu);
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}