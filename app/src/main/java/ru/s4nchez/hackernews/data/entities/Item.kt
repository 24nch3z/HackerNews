package ru.s4nchez.hackernews.data.entities

import com.google.gson.annotations.SerializedName

data class Item(
        @SerializedName("id") val id: Int,
        @SerializedName("by") val by: String?,
        @SerializedName("descendants") val descendants: Int?,
        @SerializedName("kids") val kids: List<Int>?,
        @SerializedName("score") val score: Int?,
        @SerializedName("time") var time: Long?,
        @SerializedName("title") val title: String?,
        @SerializedName("type") val type: String?,
        @SerializedName("url") val url: String
)