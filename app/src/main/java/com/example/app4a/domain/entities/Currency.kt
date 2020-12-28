package com.example.app4a.domain.entities;

data class Currency (
    var currencyName: String?="",
    var currencyUnit: String?="",
    var currencyValue: Int?=0,
    var currencyType: String?=""
)