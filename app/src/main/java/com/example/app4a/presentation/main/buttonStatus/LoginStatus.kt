package com.example.app4a.presentation.main.buttonStatus

sealed class LoginStatus

data class LoginSuccess(val email : String) : LoginStatus()

object LoginError : LoginStatus()