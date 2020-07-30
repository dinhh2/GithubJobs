package com.example.githubjobs.networking

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIendpoints {
    @GET("positions.json")
    fun getSpecificJob(@Query("search") search: String): Call<List<Jobs>>
}