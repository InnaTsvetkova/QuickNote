package com.example.quicknote.data

import com.example.quicknote.data.mappers.NoteMapper
import com.example.quicknote.domain.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDAO: NoteDAO,
    private val noteMapper: NoteMapper
): NoteRepository {

    override suspend fun addNote(text: String) {
        return noteDAO.addNote(NoteEntity(text=text))
    }

    override suspend fun deleteNote(id: Long): Int {
        return noteDAO.deleteNote(id)
    }

    override suspend fun searchNotes(text: String): List<Note> {
        return noteMapper.transform(noteDAO.searchNotes(text))
    }

    override fun getNotes(): Flow<List<Note>> {
       return noteDAO.getNotes().map {
           noteMapper.transform(it)
       }
    }
}