package com.example.app4a.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app4a.domain.entities.User
import com.example.app4a.domain.useCases.CreateUserUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(
    private val createUUC : CreateUserUseCase
) : ViewModel() {

    val livedatatest : MutableLiveData<Int> = MutableLiveData()

    init {
        livedatatest.value = 0
    }

    fun onClickInc(emailUser : String) {
        viewModelScope.launch {
            createUUC.invoke(User("test"))
        }

        livedatatest.value = (livedatatest.value ?: 0) +1 // "livedatatest?.value ?: 0" marche aussi
    }
}
