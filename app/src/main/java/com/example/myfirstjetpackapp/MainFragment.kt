package com.example.myfirstjetpackapp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfirstjetpackapp.databinding.FragmentMainBinding

class MainFragment : Fragment(),
    NoteListAdapter.IListItemListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding
    private lateinit var noteListAdapter: NoteListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        setHasOptionsMenu(true)


        binding = FragmentMainBinding.inflate(inflater)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        with(binding.recyclerView) {
            setHasFixedSize(true)
            val divider = DividerItemDecoration(
                context, LinearLayoutManager(context).orientation
            )
            viewModel.notesList?.observe(viewLifecycleOwner, Observer {
                Log.i("noteLogging", it.toString())
                noteListAdapter = NoteListAdapter(it, this@MainFragment)
                binding.recyclerView.adapter = noteListAdapter
                binding.recyclerView.layoutManager = LinearLayoutManager(activity)
            })
            addItemDecoration(divider)
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val menuId = if (this::noteListAdapter.isInitialized &&
            noteListAdapter.selectedNotes.isNotEmpty()
        )
             R.menu.main_menu_selected_items
        else R.menu.main_menu

        inflater.inflate(menuId, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_sample_note -> addSampleNotes()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addSampleNotes(): Boolean {
        viewModel.addSampleNotes()
        return true
    }

    override fun onItemClick(noteId: Int) {
        findNavController().navigate(
            MainFragmentDirections.actionEditNote().setNoteId(noteId)
        )

    }

    override fun onItemSelectionChanged() {
        requireActivity().invalidateOptionsMenu()
    }

}