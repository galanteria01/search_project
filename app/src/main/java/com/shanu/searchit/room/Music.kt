package com.shanu.searchit.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "music_list")
data class Music(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val trackId:Int,
    val artistName:String,
    val trackName:String
)