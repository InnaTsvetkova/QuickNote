package com.example.quicknote.ui.searchNote

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.quicknote.R
import com.example.quicknote.databinding.FragmentSearchNoteBinding
import com.example.quicknote.ui.NoteAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchNoteFragment(): Fragment(R.layout.fragment_search_note) {

    private val binding by viewBinding(FragmentSearchNoteBinding::bind)
    private val viewModel by viewModels<SearchNoteViewModel>()
    private val searchNoteAdapter = NoteAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.inputText.addTextChangedListener {
            viewModel.searchNote(binding.inputText.text.toString())
        }

        binding.recycler.apply {
            adapter = searchNoteAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.searchNoteLiveData.observe(viewLifecycleOwner){
            searchNoteAdapter.submitList(it)
        }
    }

}