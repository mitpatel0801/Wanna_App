package com.keep_updated.wannaapp.data.model

import com.google.gson.annotations.SerializedName

data class Team(
    val abbr: String,
    val id: Int,
    @SerializedName("is_bet_team")
    val isBetTeam: Int?,
    @SerializedName("is_favored_team")
    val isFavoredTeam: Int?,
    @SerializedName("is_underdog_team")
    val isUnderdogTeam: Int?,
    val logo: String,
    val name: String,
    @SerializedName("spread_points")
    val spreadPoints: String,
    @SerializedName("stat_id")
    val stateId: Int,
    @SerializedName("stat_provider")
    val statProvider: String,
    @SerializedName("total_draw")
    val totalDraw: Int?,
    @SerializedName("total_lose")
    val totalLose: Int?,
    @SerializedName("total_win")
    val totalWin: Int?
)