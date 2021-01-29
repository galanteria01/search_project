package com.shanu.searchit.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MusicDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMusic(music:Music)


    @Query("SELECT * FROM music_list ORDER BY id ASC")
    fun readAllData() : LiveData<List<Music>>


}