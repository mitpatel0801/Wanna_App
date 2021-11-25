package com.keep_updated.wannaapp.data.model

import com.google.gson.annotations.SerializedName


data class Data(
    @SerializedName("bet_amount")
    val betAmount: Double?,
    @SerializedName("bet_type")
    val betType: String?,
    @SerializedName("away_team")
    val awayTeam: Team?,
    val better: Player?,
    @SerializedName("home_team")
    val homeTeam: Team?,
    val taker: Player?,
    val event: Event?
)
