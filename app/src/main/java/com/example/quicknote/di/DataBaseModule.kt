package com.example.quicknote.di

import android.content.Context
import androidx.room.Room
import com.example.quicknote.data.NoteDAO
import com.example.quicknote.data.NoteDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    fun provideNoteDB(@ApplicationContext context: Context): NoteDataBase {
        return Room.databaseBuilder(
            context,
            NoteDataBase::class.java,
            NoteDataBase.DB_NAME
        ).build()
    }

    @Provides
    fun provideNoteDAO(dataBase: NoteDataBase): NoteDAO{
        return dataBase.notesDAO()
    }
}