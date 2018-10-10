package ru.s4nchez.hackernews.di

import dagger.Component
import ru.s4nchez.hackernews.ui.list.ListView
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject(view: ListView)
}