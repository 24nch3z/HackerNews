package ru.s4nchez.hackernews.data.datasource

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.s4nchez.hackernews.data.entities.Item

interface APIInterface {

    @GET("newstories.json")
    fun getNewStories(): Single<List<Int>>

    @GET("item/{id}.json")
    fun getItem(@Path("id") id: Int): Single<Item>
}