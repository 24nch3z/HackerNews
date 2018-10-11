package ru.s4nchez.hackernews.interactors

import io.reactivex.Single
import ru.s4nchez.hackernews.data.entities.Item

interface NewsInteractor {
    fun getNewStories(): Single<List<Int>>
    fun getItem(id: Int): Single<Item>
}