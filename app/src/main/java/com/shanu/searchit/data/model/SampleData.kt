package com.shanu.searchit.data.model

import com.google.gson.annotations.SerializedName

data class SampleData (

	@SerializedName("resultCount") val resultCount : Int,
	@SerializedName("results") val results : List<Results>
)