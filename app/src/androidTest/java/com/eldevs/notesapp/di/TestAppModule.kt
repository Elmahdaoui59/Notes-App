package com.eldevs.notesapp.di

import android.content.Context
import androidx.room.Room
import com.eldevs.notesapp.feature_note.data.data_source.NoteDatabase
import com.eldevs.notesapp.feature_note.data.repository.NoteRepositoryImpl
import com.eldevs.notesapp.feature_note.domain.repository.NoteRepository
import com.eldevs.notesapp.feature_note.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(
        @ApplicationContext context: Context
    ): NoteDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            NoteDatabase::class.java,
        )
            .build()
    }

    @Provides
    @Singleton
    fun providesNotreRepository(noteDatabase: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(noteDatabase.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository)
        )
    }
}