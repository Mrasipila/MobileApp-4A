package com.example.app4a.data.repository

import com.example.app4a.domain.entities.Currency
import com.example.app4a.data.remote.RetrofitClient
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import com.example.app4a.presentation.main.ui.MainActivity
import io.reactivex.Observable
import retrofit2.Callback

class RetrofitRepo (
    private val cryptoApiCall : RetrofitClient
) {

/*    fun makeCryptoCall() : Currency? {
        cryptoApiCall.start()
        cryptoApiCall.fetchedDataLiveData.observe(this, Observer {
            when(it)
        })
    }*/
    fun checkApiStatus() : Boolean? {
        return cryptoApiCall.dataStatusApi
    }

    fun getCryptoResponse(currCb : Callback<List<Currency>>) {
        cryptoApiCall.start(currCb)
    }

}


