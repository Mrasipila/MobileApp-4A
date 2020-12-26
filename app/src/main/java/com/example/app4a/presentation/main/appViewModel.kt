package com.example.app4a.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class appViewModel : ViewModel() {

    val livedatatest : MutableLiveData<Int> = MutableLiveData()

    init {
        livedatatest.value = 0
    }

    fun onClickInc() {
        livedatatest.value = (livedatatest.value ?: 0) +1 // "livedatatest?.value ?: 0" marche aussi
    }
}
