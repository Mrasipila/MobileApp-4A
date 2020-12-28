package com.example.app4a.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.app4a.data.remote.RetrofitHTTPRequest
import com.example.app4a.domain.entities.Currency
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

val retrofitHTTPRequest : RetrofitHTTPRequest = TODO()

fun fetchAllPosts(): LiveData<List<Currency>> {
    val data = MutableLiveData<List<Currency>>()
    retrofitHTTPRequest?.fetchAllPosts()?.enqueue(object : Callback<List<Currency>> {
        override fun onFailure(call: Call<List<Currency>>, t: Throwable) {
            data.value = null
        }

        override fun onResponse(
                call: Call<List<Currency>>,
                response: Response<List<Currency>>
        ) {

            val res = response.body()
            if (response.code() == 200 &&  res!=null){
                data.value = res
            }else{
                data.value = null
            }
        }
    })
    return data
}