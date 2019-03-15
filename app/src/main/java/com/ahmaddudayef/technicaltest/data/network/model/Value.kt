package com.ahmaddudayef.technicaltest.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Value(
    var type: String = "TYPE",

    @SerializedName("categories")
    @Expose
    val categories: List<String>? = null,
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @Expose
    @SerializedName("joke")
    val joke: String? = null
)