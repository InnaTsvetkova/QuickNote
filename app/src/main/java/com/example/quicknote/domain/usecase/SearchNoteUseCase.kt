package com.example.quicknote.domain.usecase

import com.example.quicknote.data.NoteRepository
import com.example.quicknote.domain.Note
import javax.inject.Inject

class SearchNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(text: String): List<Note>{
        return noteRepository.searchNotes(text)
    }
}