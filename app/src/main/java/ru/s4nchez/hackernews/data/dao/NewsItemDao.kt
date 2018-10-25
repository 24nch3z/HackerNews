package ru.s4nchez.hackernews.data.dao

import android.arch.persistence.room.*
import io.reactivex.Single
import ru.s4nchez.hackernews.data.entities.NewsItem

@Dao
interface NewsItemDao {

    @Query("SELECT * FROM NewsItem WHERE id = :id")
    fun getById(id: Int): Single<NewsItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: NewsItem): Long

    @Update
    fun update(item: NewsItem)

    @Delete
    fun delete(item: NewsItem)
}