package com.example.quicknote.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class], version = NoteDataBase.DB_VERSION)
abstract class NoteDataBase() : RoomDatabase() {

    companion object{
         const val DB_VERSION = 1
         const val DB_NAME="notesDB"
    }
    abstract fun notesDAO(): NoteDAO

}