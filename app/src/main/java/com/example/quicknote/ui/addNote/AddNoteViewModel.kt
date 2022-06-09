package com.example.quicknote.ui.addNote

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quicknote.domain.usecase.AddNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase
) : ViewModel() {

    val navigationLiveData = MutableLiveData<Any>()

    fun addNote(text: String) {
        viewModelScope.launch {
            addNoteUseCase(text)
            navigationLiveData.value = Any()
        }
    }
}