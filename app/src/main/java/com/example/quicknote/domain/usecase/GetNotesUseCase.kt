package com.example.quicknote.domain.usecase

import com.example.quicknote.data.NoteRepository
import com.example.quicknote.domain.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(): Flow<List<Note>>{
        return noteRepository.getNotes()
    }
}