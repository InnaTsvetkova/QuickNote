package com.example.quicknote.data

import com.example.quicknote.domain.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun addNote(text: String)

    suspend fun deleteNote(id: Long): Int

    suspend fun searchNotes(text: String): List<Note>

    fun getNotes(): Flow<List<Note>>
}