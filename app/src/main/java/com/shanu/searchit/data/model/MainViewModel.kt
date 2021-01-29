package com.shanu.searchit.data.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shanu.searchit.data.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel (private val repository: Repository):ViewModel() {
    val myResponseData: MutableLiveData<Response<SampleData>> = MutableLiveData()

    fun getData(term:String){
        viewModelScope.launch {
            val response = repository.getData(term)
            myResponseData.value = response
        }
    }
}