package ru.s4nchez.hackernews.data.datasource

import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {

    @GET("newstories.json")
    fun getNewStories(): Call<List<Long>>
}