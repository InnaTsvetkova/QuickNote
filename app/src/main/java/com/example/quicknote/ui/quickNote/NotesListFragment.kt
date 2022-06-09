package com.example.quicknote.ui.quickNote

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.quicknote.R
import com.example.quicknote.databinding.FragmentNotesListBinding
import com.example.quicknote.ui.NoteAdapter
import com.example.quicknote.ui.SwipeToDeleteNote
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesListFragment(
) : Fragment(R.layout.fragment_notes_list) {

    private val noteAdapter = NoteAdapter()
    private val viewModel by viewModels<NotesListViewModel>()
    private val binding by viewBinding(FragmentNotesListBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.getNotes()

        binding.recycler.apply {
            adapter = noteAdapter
            layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)

            val swipeHandler = object : SwipeToDeleteNote(requireContext()) {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.bindingAdapterPosition
                    viewModel.deleteNote(position)
                }
            }
            ItemTouchHelper(swipeHandler).attachToRecyclerView(this)
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_notesListFragment_to_addNoteFragment)
        }

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.search -> {
                    findNavController().navigate(R.id.action_notesListFragment_to_searchNoteFragment)
                    true
                }
                else -> false
            }
        }

        viewModel.notesLiveData.observe(viewLifecycleOwner) {
            noteAdapter.submitList(it)
        }
    }

}