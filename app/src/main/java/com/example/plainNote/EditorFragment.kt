package com.example.plainNote

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.plainNote.databinding.FragmentEditorBinding

class EditorFragment : Fragment() {

    private lateinit var viewModel: EditorViewModel
    private val args: EditorFragmentArgs by navArgs()
    private lateinit var binding: FragmentEditorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.let {
            it.setHomeButtonEnabled(true)
            it.setDisplayShowHomeEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_check)
        }

        setHasOptionsMenu(true)

        requireActivity().title =
            if (args.noteId == NEW_NOTE_ID)
                getString(R.string.new_note)
            else getString(R.string.edit_note)

        viewModel = ViewModelProvider(this)[EditorViewModel::class.java]
        binding = FragmentEditorBinding.inflate(inflater)
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    saveAndReturn()
                }

            }

        )
        viewModel.getNoteById(args.noteId)
        viewModel.currentNote.observe(viewLifecycleOwner, Observer {
            val savedNote = savedInstanceState?.getString(NOTE_TEXT_KEY)
            val savedCursor = savedInstanceState?.getInt(CURSOR_POSITION_KEY) ?: 0
            binding.editor.setText(savedNote ?: it.text)
            binding.editor.setSelection(savedCursor)
        })


        return binding.root
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> saveAndReturn()
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun saveAndReturn(): Boolean {
        val imm = requireActivity()
            .getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)

        viewModel.currentNote.value?.text = binding.editor.text.toString()
        viewModel.updateNote()

        findNavController().navigateUp()
        return true
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    override fun onSaveInstanceState(outState: Bundle) {
        with(binding.editor) {
            outState.putString(NOTE_TEXT_KEY, text.toString())
            outState.putInt(CURSOR_POSITION_KEY, selectionStart)
        }
        super.onSaveInstanceState(outState)
    }
}