package com.example.app4a.domain.entities;

import com.google.gson.annotations.SerializedName

data class  Currency (
    var id : String?="",
    var name : String?="",
    var year_established : Int?=0,
    var country : String?="",
    var description : String?="",
    var url : String?="",
    var image : String?="",
    var has_trading_incentive : Boolean?=null,
    var trust_score : Int?=0,
    var trust_score_rank : Int?=0,
    var trade_volume_24h_btc : Float?=0f,
    var trade_volume_24h_btc_normalized : Float?=0f
)
