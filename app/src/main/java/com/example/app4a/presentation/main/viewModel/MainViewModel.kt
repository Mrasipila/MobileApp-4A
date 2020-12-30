package com.example.app4a.presentation.main.viewModel

import android.renderscript.Sampler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app4a.domain.entities.Currency
import com.example.app4a.domain.entities.User
import com.example.app4a.domain.useCases.CreateUserUseCase
import com.example.app4a.domain.useCases.GetAllUserUseCase
import com.example.app4a.domain.useCases.GetCurrencyUseCase
import com.example.app4a.domain.useCases.GetUserUseCase
import com.example.app4a.presentation.main.buttonStatus.*
import com.example.app4a.presentation.main.ui.activityList.AppListActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(
    private val createUUC : CreateUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val getAllUserUseCase: GetAllUserUseCase,
    private val getCurrencyUseCase: GetCurrencyUseCase
) : ViewModel() {

    val loginLiveData : MutableLiveData<LoginStatus> = MutableLiveData();
    val signInLiveData : MutableLiveData<SignInStatus> = MutableLiveData();
    val ApiLiveData : MutableLiveData<ApiStatus> = MutableLiveData();
    //var temporel : List<Currency>? = null


    fun fetchDataForAdapter(callback: Callback<List<Currency>>) {
        getCurrencyUseCase.invoke(callback)
    }

    fun onClickedLogin(emailUser: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user : User? = getUserUseCase.invoke(emailUser,password)
            val loginStatus : LoginStatus = if(user != null) {
                LoginSuccess(user.email)
            } else {
                LoginError
            }

            withContext(Dispatchers.Main) {
                loginLiveData.value = loginStatus
            }
        }
    }

    // Check Correct user entry and create new user in db if everything is ok
    fun onClickedSignIn(emailUser: String, password: String) {
        val regex0 = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$".toRegex()
        val regex = "[a-zA-Z0-9]{1,16}@[a-zA-Z0-9]{4,8}.[a-zA-Z]{2,3}".toRegex()
        val user: User = User(emailUser, password)

        viewModelScope.launch(Dispatchers.Default) {
            val us = getUserUseCase.invoke(emailUser,password)
            val au = getAllUserUseCase.invoke(user)
            val signInStatus: SignInStatus = if (regex.matches(emailUser) && regex0.matches(password) && us==null) {
                createUUC.invoke(user)
                SignInSuccess(emailUser,au)
            } else {
                SignInError
            }

            withContext(Dispatchers.Main) {
                signInLiveData.value = signInStatus
            }
        }
    }
}