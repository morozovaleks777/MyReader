package com.example.myreader.model


import com.google.gson.annotations.SerializedName

data class IndustryIdentifier(
    @SerializedName("identifier")
    val identifier: String,
    @SerializedName("type")
    val type: String
)