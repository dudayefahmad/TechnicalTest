package com.ahmaddudayef.technicaltest.data.network.model

import com.google.gson.annotations.SerializedName

data class ResponseData(
    @SerializedName("type")
    val type: String,
    @SerializedName("value")
    val value: MutableList<Value>
)