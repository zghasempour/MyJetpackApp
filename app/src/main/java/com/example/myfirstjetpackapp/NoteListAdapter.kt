package com.example.myfirstjetpackapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstjetpackapp.data.NoteEntity
import com.example.myfirstjetpackapp.databinding.ListItemBinding
import java.util.zip.Inflater

class NoteListAdapter(private val notesList : List<NoteEntity>) :
    RecyclerView.Adapter<NoteListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView : View):
        RecyclerView.ViewHolder(itemView) {
            val binding = ListItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notesList[position]
        with(holder.binding){
            noteText.text = note.text
        }

    }

    override fun getItemCount() = notesList.size
}