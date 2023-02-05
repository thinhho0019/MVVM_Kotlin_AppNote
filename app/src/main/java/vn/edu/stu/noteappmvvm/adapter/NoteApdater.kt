package vn.edu.stu.noteappmvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import vn.edu.stu.noteappmvvm.databinding.ItemNoteRecyclerviewBinding
import vn.edu.stu.noteappmvvm.fragments.HomeFragmentDirections
import vn.edu.stu.noteappmvvm.model.Notes

class NoteApdater:RecyclerView.Adapter<NoteApdater.noteHolder>() {
    private var binding :ItemNoteRecyclerviewBinding ?=null
    class noteHolder(item:ItemNoteRecyclerviewBinding):
        RecyclerView.ViewHolder(item.root)

    private val diffCallBack = object :DiffUtil.ItemCallback<Notes>(){ //ham so sanh su khac nhau trong recycler view
        override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean { //kiem tra su khac nhau hai 2 item ta dung khoa chinh
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return oldItem ==newItem
        }

    }

    val differ = AsyncListDiffer(this,diffCallBack);
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): noteHolder {
        binding = ItemNoteRecyclerviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return noteHolder(binding!!)
    }

    override fun onBindViewHolder(holder: noteHolder, position: Int) {
        val currentItem = differ.currentList[position]
        holder.itemView.apply {
            binding?.itemTitle?.text = currentItem.title
            binding?.itemBodyNote?.text = currentItem.noteBody //? la bien co the null
        }.setOnClickListener{v->
            val direct = HomeFragmentDirections.actionHomeFragmentToUpdateFragment(currentItem) ;
            v.findNavController().navigate(
                direct
            )
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}