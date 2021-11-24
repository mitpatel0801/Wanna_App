package com.keep_updated.wannaapp.data.model

import com.google.gson.annotations.SerializedName

data class Player(
    val id: Int,
    val image: String?,
    @SerializedName("username")
    val userName: String?,
    @SerializedName("total_draw")
    val totalDraw: Int?,
    @SerializedName("total_lose")
    val totalLose: Int?,
    @SerializedName("total_tie")
    val totalTie: Int?,
    @SerializedName("total_win")
    val totalWin: Int?
)