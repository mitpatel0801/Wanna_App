package com.keep_updated.wannaapp.data.model

import com.google.gson.annotations.SerializedName

data class Bets(
    val count:Int,
    @SerializedName("data")
    val data:List<Data>
)
