package com.example.app4a.data.remote

import androidx.lifecycle.MutableLiveData
import com.example.app4a.domain.entities.Currency
import com.example.app4a.presentation.main.buttonStatus.ApiStatus
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://api.coingecko.com"

class RetrofitClient {
    var dataStatusApi: Boolean? = null
    var fetchedData: List<Currency>? = null
    var fetchedDataLiveData: MutableLiveData<ApiStatus> = MutableLiveData();
    //var apiStatus: ApiStatus? = null

    fun start(currCb : Callback<List<Currency>> ) {// Observable<List<Currency>> {
        /* val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()*/

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            //   .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val cryptocurrencyService: RetrofitHTTPRequest =
            retrofit.create(RetrofitHTTPRequest::class.java)

        val call: Call<List<Currency>> = cryptocurrencyService.fetchAllCurrency()
        call.enqueue(currCb)
    }
}

/*
    private fun handleResults(marketList: List<Currency>?) {
        if (marketList != null && marketList.size != 0) {
            fetchedData=marketList
            dataStatusApi = true
        } else {
            dataStatusApi = false
        }
    }

    private fun handleError(t: Throwable) {
        dataStatusApi = false
    }*/
