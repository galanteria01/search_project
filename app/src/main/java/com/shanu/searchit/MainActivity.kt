package com.shanu.searchit

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shanu.searchit.adapter.Adapter
import com.shanu.searchit.adapter.OfflineAdapter
import com.shanu.searchit.data.model.MainViewModel
import com.shanu.searchit.data.model.MainViewModelFactory
import com.shanu.searchit.data.repository.Repository
import com.shanu.searchit.room.Music
import com.shanu.searchit.room.MusicViewModel

class MainActivity : AppCompatActivity() {
    // Initialised view models and adapters
    private lateinit var viewModel: MainViewModel
    private lateinit var musicViewModel: MusicViewModel
    var adapter: Adapter?=null
    var offlineAdapter: OfflineAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val searchView: SearchView = findViewById(R.id.searchView)
        val recyclerView:RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                val term = query!!.toLowerCase().replace("\\s", "")
                val repository = Repository()
                val viewModelFactory = MainViewModelFactory(repository)
                viewModel = ViewModelProvider(
                    this@MainActivity,
                    viewModelFactory
                ).get(MainViewModel::class.java)
                musicViewModel =
                    ViewModelProvider(this@MainActivity).get(MusicViewModel::class.java)
                viewModel.getData(term)
                viewModel.myResponseData.observe(this@MainActivity, { response ->
                    // Valid if internet works and the response is captured
                    if (response.isSuccessful) {
                        adapter = Adapter(response.body()!!.results)
                        recyclerView.adapter = adapter
                        response.body()!!.results.forEach { data ->
                            musicViewModel.addMusic(
                                Music(
                                    0,
                                    data.trackId,
                                    data.artistName,
                                    data.trackCensoredName
                                )
                            )
                        }
                    }
                    // Valid if internet doesn't works and database is scanned
                    else {
                        musicViewModel.readAllData.observe(this@MainActivity, Observer { music ->
                            music.forEach { data ->
                                var neededData = arrayListOf<Music>()
                                if (data.artistName == term) {
                                    neededData.add(data)
                                }
                                offlineAdapter = OfflineAdapter(neededData.toList())
                                recyclerView.adapter = adapter
                            }
                        })
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