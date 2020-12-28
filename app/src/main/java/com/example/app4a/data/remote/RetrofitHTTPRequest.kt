package com.example.app4a.data.remote

import com.example.app4a.domain.entities.Currency
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitHTTPRequest {
    @GET("posts")
    fun fetchAllPosts(): Call<List<Currency>>
}