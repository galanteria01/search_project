package com.shanu.searchit.data.api

import SampleData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("search")
    suspend fun getData(
        @Query("term") searchTerm:String
    ) : Response<SampleData>

}