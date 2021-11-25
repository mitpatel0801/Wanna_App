package com.keep_updated.wannaapp.data.model

import com.google.gson.annotations.SerializedName

data class Event(
    val id: Int,
    @SerializedName("away_score")
    val awayScore: Int?,
    @SerializedName("away_settled_score")
    val awaySettledScore: Int?,
    val clock: String?,
    @SerializedName("game_status")
    val gameStatus: String?,
    @SerializedName("home_score")
    val homeScore: Int?,
    @SerializedName("home_settled_score")
    val homeSettledScore: Int?,
    @SerializedName("is_having_quarter")
    val isHavingQuarter: Boolean?,
    @SerializedName("is_having_regulation_time")
    val isHavingRegulationTime: Boolean?,
    val quarter: Int?,
    val scheduled: String?,
    @SerializedName("stat_id")
    val statId: Int?,
    @SerializedName("stat_provider")
    val statProvider: String?
)