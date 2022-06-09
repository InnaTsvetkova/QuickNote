package com.example.quicknote.ui.searchNote

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quicknote.domain.Note
import com.example.quicknote.domain.usecase.SearchNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchNoteViewModel @Inject constructor(
    private val searchNoteUseCase: SearchNoteUseCase
): ViewModel() {
    val searchNoteLiveData = MutableLiveData<List<Note>>()

    fun searchNote(text: String){
        viewModelScope.launch {
            searchNoteLiveData.value = searchNoteUseCase.invoke(text)
        }
    }
}