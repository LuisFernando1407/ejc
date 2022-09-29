package com.ejc.flow.main.data

import com.google.gson.annotations.SerializedName

data class Movie(
    val category: String,
    val imageUrl: String,
    val name: String,

    @SerializedName("desc")
    val description: String
)