package com.shanu.searchit.adapter

import Results
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shanu.searchit.R

class Adapter(val searchResults:List<Results>) :RecyclerView.Adapter<Adapter.viewHolder>(){


    override fun getItemCount(): Int {
        return searchResults.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        return holder.bind(searchResults[position])
    }

    inner class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var songName: TextView = itemView.findViewById(R.id.songName)
        fun bind(song: Results){
            songName.text = song.trackName
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.song_ticket,parent,false)
        return viewHolder(view)
    }


}