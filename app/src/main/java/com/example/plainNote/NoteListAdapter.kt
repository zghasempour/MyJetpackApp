package com.example.plainNote

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstjetpackapp.R
import com.example.myfirstjetpackapp.databinding.ListItemBinding
import com.example.plainNote.data.NoteEntity

class NoteListAdapter(
    private val notesList: List<NoteEntity>,
    private val listener: IListItemListener
) :
    RecyclerView.Adapter<NoteListAdapter.ViewHolder>() {

    val selectedNotes = arrayListOf<NoteEntity>()

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val binding = ListItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notesList[position]
        with(holder.binding) {
            noteText.text = note.text
            root.setOnClickListener {
                listener.onItemClick(note.id)
            }
            fab.setOnClickListener {
                if (selectedNotes.contains(note)) {
                    selectedNotes.remove(note)
                    fab.setImageResource(R.drawable.ic_note)
                } else {
                    selectedNotes.add(note)
                    fab.setImageResource(R.drawable.ic_check)
                }
                listener.onItemSelectionChanged()
            }
                fab.setImageResource(
                    if (selectedNotes.contains(note))
                        R.drawable.ic_check
                else R.drawable.ic_note
                )


        }

    }

    override fun getItemCount() = notesList.size

    interface IListItemListener {
        fun onItemClick(noteId: Int)
        fun onItemSelectionChanged()
    }
}