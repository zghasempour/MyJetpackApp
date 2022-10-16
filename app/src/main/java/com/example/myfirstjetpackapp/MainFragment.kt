package com.example.myfirstjetpackapp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfirstjetpackapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding : FragmentMainBinding
    private lateinit var noteListAdapter: NoteListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        with(binding.recyclerView){
            setHasFixedSize(true)
            val divider = DividerItemDecoration(
            context, LinearLayoutManager(context).orientation
            )
            viewModel.notesList.observe(viewLifecycleOwner, Observer {
                Log.i("noteLogging",it.toString())
                noteListAdapter = NoteListAdapter(it)
                binding.recyclerView.adapter = noteListAdapter
                binding.recyclerView.layoutManager = LinearLayoutManager(activity)
            })
            addItemDecoration(divider)
        }
        return binding.root
    }

}