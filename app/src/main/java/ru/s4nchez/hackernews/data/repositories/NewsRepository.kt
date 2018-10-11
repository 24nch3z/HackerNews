package ru.s4nchez.hackernews.data.repositories

import io.reactivex.Single
import ru.s4nchez.hackernews.data.entities.Item

interface NewsRepository {
    fun getNewStories(): Single<List<Int>>
    fun getItem(id: Int): Single<Item>
}