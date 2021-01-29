package com.shanu.searchit

import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shanu.searchit.adapter.Adapter
import com.shanu.searchit.data.model.MainViewModel
import com.shanu.searchit.data.model.MainViewModelFactory
import com.shanu.searchit.data.repository.Repository
import com.shanu.searchit.room.Music
import com.shanu.searchit.room.MusicViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var musicViewModel: MusicViewModel
    var adapter: Adapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val searchView: SearchView = findViewById(R.id.searchView)
        val recyclerView:RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                var term = query!!.toLowerCase().replace("\\s","")
                val repository = Repository()
                val viewModelFactory = MainViewModelFactory(repository)
                viewModel = ViewModelProvider(this@MainActivity,viewModelFactory).get(MainViewModel::class.java)
                musicViewModel = ViewModelProvider(this@MainActivity).get(MusicViewModel::class.java)
                viewModel.getData(term)
                viewModel.myResponseData.observe(this@MainActivity, { response ->
                    if(response.isSuccessful){
                        adapter = Adapter(response.body()!!.results)
                        recyclerView.adapter = adapter
                        musicViewModel.addMusic(
                            Music(
                            0,
                            response.body()!!.results[1].trackId,
                            response.body()!!.results[1].artistName,
                            response.body()!!.results[1].trackName
                        )
                        )
                        Log.d("working","Data succesfully added")
                    }
                })
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
}