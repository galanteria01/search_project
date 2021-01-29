package com.shanu.searchit.data.repository

import com.shanu.searchit.data.model.SampleData
import com.shanu.searchit.data.api.RetrofitInstance
import retrofit2.Response

class Repository {

    suspend fun getData(term:String) : Response<SampleData>{
        return RetrofitInstance.api.getData(searchTerm = term)
    }

}