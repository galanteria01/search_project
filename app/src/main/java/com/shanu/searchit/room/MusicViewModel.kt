package com.shanu.searchit.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MusicViewModel(application: Application) : AndroidViewModel(application){

    private val readAllData : LiveData<List<Music>>
    private val repository: MusicRepository

    init{
        val musicDao = MusicDatabase.getDatabase(application).musicDao()
        repository = MusicRepository(musicDao)
        readAllData = repository.readAllData
    }

    fun addMusic(music: Music){
        viewModelScope.launch (Dispatchers.IO){
            repository.addMusic(music)
        }
    }

}