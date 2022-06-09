package com.example.quicknote.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {

    @Insert
    suspend fun addNote(note: NoteEntity)

    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun deleteNote(id: Long): Int

    @Query("SELECT * FROM notes WHERE text LIKE :text")
    suspend fun searchNotes(text: String): List<NoteEntity>

    @Query("SELECT * FROM notes")
    fun getNotes(): Flow<List<NoteEntity>>
}