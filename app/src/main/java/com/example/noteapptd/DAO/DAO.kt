package com.example.noteapptd

import androidx.room.*
import com.example.noteapptd.entitites.Notes


@Dao
interface DAO {

    @Query("SELECT * FROM Notes ORDER BY id DESC")
    suspend fun getAllNotes() : List<Notes>

    @Query("SELECT * FROM Notes WHERE id =:id")
    suspend fun getSpecificNote(id:Int) : Notes

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(note:Notes)

    @Delete
    suspend fun deleteNote(note:Notes)

    @Query("DELETE FROM Notes WHERE id =:id")
    suspend fun deleteSpecificNote(id:Int)

    @Update
    suspend fun updateNote(note:Notes)
}