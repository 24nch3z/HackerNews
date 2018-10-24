package ru.s4nchez.hackernews.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import ru.s4nchez.hackernews.data.dao.NewsItemDao
import ru.s4nchez.hackernews.data.entities.NewsItem

@Database(
        entities = [NewsItem::class],
        version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsItemDao(): NewsItemDao
}