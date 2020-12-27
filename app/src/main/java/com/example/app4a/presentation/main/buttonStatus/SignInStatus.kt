package com.example.app4a.presentation.main.buttonStatus

sealed class SignInStatus

data class SignInSuccess(val email : String, val allUsers : String) : SignInStatus()

object SignInError : SignInStatus()