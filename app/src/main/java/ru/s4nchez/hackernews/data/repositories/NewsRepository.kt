package ru.s4nchez.hackernews.data.repositories

import io.reactivex.Single
import ru.s4nchez.hackernews.data.entities.Item

interface NewsRepository {
    fun getNewStories(): Single<Boolean>
    fun loadNextPage(): Single<List<Item>>
}