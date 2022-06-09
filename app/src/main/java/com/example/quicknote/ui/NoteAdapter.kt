package com.example.quicknote.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quicknote.databinding.ItemNoteBinding
import com.example.quicknote.domain.Note

class NoteAdapter(
) : ListAdapter<Note, NoteAdapter.NoteHolder>(NoteCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NoteHolder(private val viewBinding: ItemNoteBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(note: Note) {
            viewBinding.text.text = note.text
        }
    }
}

object NoteCallBack: DiffUtil.ItemCallback<Note>(){
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}