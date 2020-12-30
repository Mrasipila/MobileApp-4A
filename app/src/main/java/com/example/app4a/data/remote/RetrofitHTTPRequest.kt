package com.example.app4a.data.remote

import com.example.app4a.domain.entities.Currency
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitHTTPRequest {
    @GET("/api/v3/exchanges")
    fun fetchAllCurrency(): Call<List<Currency>>
}