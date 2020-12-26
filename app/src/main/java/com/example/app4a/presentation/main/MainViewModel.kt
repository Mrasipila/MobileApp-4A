package com.example.app4a.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app4a.domain.entities.User
import com.example.app4a.domain.useCases.CreateUserUseCase
import com.example.app4a.domain.useCases.GetUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(
    private val createUUC : CreateUserUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    val livedatatest : MutableLiveData<Int> = MutableLiveData()

    init {
        livedatatest.value = 0
    }

    fun onClickInc(emailUser : String) {
        viewModelScope.launch(Dispatchers.IO) {
            createUUC.invoke(User("test"))
            val user : User = getUserUseCase.invoke("test")
            val debug = "debug"
        }

        livedatatest.value = (livedatatest.value ?: 0) +1 // "livedatatest?.value ?: 0" marche aussi
    }
}
