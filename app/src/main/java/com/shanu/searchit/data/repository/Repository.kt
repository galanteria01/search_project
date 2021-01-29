package com.shanu.searchit.data.repository

import com.shanu.searchit.data.api.RetrofitInstance
import com.shanu.searchit.data.model.SampleData
import retrofit2.Response

// Repository created to connect UI to ViewModel

class Repository {

    suspend fun getData(term:String) : Response<SampleData>{
        return RetrofitInstance.api.getData(searchTerm = term)
    }

}