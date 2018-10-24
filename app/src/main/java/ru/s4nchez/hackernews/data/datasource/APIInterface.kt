package ru.s4nchez.hackernews.data.datasource

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.s4nchez.hackernews.data.entities.NewsItem

val BASE_URL = "https://hacker-news.firebaseio.com/v0/"

interface APIInterface {

    @GET("newstories.json")
    fun getNewStories(): Single<List<Int>>

    @GET("item/{id}.json")
    fun getItem(@Path("id") id: Int): Single<NewsItem>
}