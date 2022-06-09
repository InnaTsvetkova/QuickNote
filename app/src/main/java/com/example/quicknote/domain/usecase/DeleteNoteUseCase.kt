package com.example.quicknote.domain.usecase

import com.example.quicknote.data.NoteRepository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(id: Long): Int{
        return noteRepository.deleteNote(id)
    }
}