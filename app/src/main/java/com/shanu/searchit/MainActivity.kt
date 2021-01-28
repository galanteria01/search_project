package com.shanu.searchit

import SampleData
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shanu.searchit.adapter.Adapter
import com.shanu.searchit.data.model.MainViewModel
import com.shanu.searchit.data.model.MainViewModelFactory
import com.shanu.searchit.data.repository.Repository

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    val data:SampleData?=null
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
                viewModel.getData(term)
                viewModel.myResponseData.observe(this@MainActivity, Observer { response ->
                    if(response.isSuccessful){
                        Log.d("working",response.body()!!.results[1].artistName.toString())
                        adapter = Adapter(response.body()!!.results)
                        recyclerView.adapter = adapter

                    }
                })
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }


        })

        fun getResults(query:String,recyclerView: RecyclerView){

        }
    }


    fun setChanges(){
        Log.d("Working","Working")
    }

}