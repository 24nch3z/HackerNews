package ru.s4nchez.hackernews.data.repositories

import io.reactivex.Single
import ru.s4nchez.hackernews.data.entities.NewsItem

interface NewsRepository {
    fun getNewStories(): Single<Boolean>
    fun loadNextPage(offset: Int, count: Int): Single<List<NewsItem>>
}