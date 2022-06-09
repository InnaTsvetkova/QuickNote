package com.example.quicknote.domain.usecase

import com.example.quicknote.data.NoteRepository
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(text: String){
        noteRepository.addNote(text)
    }
}