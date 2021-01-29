package com.shanu.searchit.room

import androidx.lifecycle.LiveData

// Repository created to connect UI to ViewModel

class MusicRepository (private val musicDao: MusicDao){

    val readAllData: LiveData<List<Music>> = musicDao.readAllData()
    suspend fun addMusic(music:Music){
        musicDao.addMusic(music)
    }
}