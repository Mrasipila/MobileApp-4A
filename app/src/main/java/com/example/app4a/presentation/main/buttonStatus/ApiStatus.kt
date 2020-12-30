package com.example.app4a.presentation.main.buttonStatus

import com.example.app4a.domain.entities.Currency

sealed class ApiStatus

data class ApiSuccess(val currency: List<Currency>?) : ApiStatus()

object ApiError : ApiStatus()