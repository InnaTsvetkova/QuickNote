package com.example.quicknote.ui.quickNote

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quicknote.domain.usecase.GetNotesUseCase
import com.example.quicknote.domain.Note
import com.example.quicknote.domain.usecase.DeleteNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesListViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {

    val notesLiveData = MutableLiveData<List<Note>>()

    fun getNotes() {
        viewModelScope.launch {
            getNotesUseCase.invoke().collect {
                notesLiveData.value = it
            }
        }
    }

    fun deleteNote(position: Int){
        val id = notesLiveData.value.orEmpty()[position].id
        viewModelScope.launch {
            deleteNoteUseCase.invoke(id)
        }
    }
}