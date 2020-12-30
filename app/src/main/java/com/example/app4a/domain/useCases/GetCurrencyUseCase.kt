package com.example.app4a.domain.useCases

import com.example.app4a.data.repository.RetrofitRepo
import com.example.app4a.domain.entities.Currency
import io.reactivex.Observable
import retrofit2.Callback

class GetCurrencyUseCase(
    private val retrofitRepo : RetrofitRepo
) {
    fun invoke(currCb : Callback<List<Currency>>) {
        retrofitRepo.getCryptoResponse(currCb)
    }
}