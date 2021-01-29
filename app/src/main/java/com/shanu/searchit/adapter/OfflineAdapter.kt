package com.shanu.searchit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shanu.searchit.R
import com.shanu.searchit.room.Music

class OfflineAdapter(private val searchResults: List<Music>) :RecyclerView.Adapter<OfflineAdapter.ViewHolder>(){


    override fun getItemCount(): Int {
        return searchResults.size
    }

    // Created class viewholder for secondary offline adapter
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private var songName: TextView = itemView.findViewById(R.id.songName)
        private var artistName :TextView = itemView.findViewById(R.id.artistName)
        fun bind(song: Music){
            songName.text = song.trackName
            artistName.text = song.artistName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.song_ticket,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(searchResults[position])
    }


}