package com.example.noteapptd.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noteapptd.DAO
import com.example.noteapptd.entitites.Notes


@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): DAO

    companion object{
        var noteDatabase: NoteDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): NoteDatabase {
            if(noteDatabase == null){
                noteDatabase = Room.databaseBuilder(
                    context,
                    NoteDatabase::class.java,
                    "note.db"
                ).build()
            }
            return  noteDatabase!!
        }
    }
}