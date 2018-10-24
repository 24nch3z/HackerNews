package ru.s4nchez.hackernews.data.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "NewsItem")
data class NewsItem(
        @PrimaryKey
        @SerializedName
        ("id") val id: Int,

        @SerializedName("by")
        val by: String?,

        @SerializedName("descendants")
        val descendants: Int?,

        @SerializedName("score")
        val score: Int?,

        @SerializedName("time")
        var time: Long?,

        @SerializedName("title")
        val title: String?,

        @SerializedName("type")
        val type: String?,

        @SerializedName("url")
        val url: String?
)