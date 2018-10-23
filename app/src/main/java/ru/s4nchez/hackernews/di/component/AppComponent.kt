package ru.s4nchez.hackernews.di.component

import dagger.Component
import ru.s4nchez.hackernews.di.module.AppModule
import ru.s4nchez.hackernews.di.module.NetModule
import ru.s4nchez.hackernews.di.module.NewsModule
import javax.inject.Singleton

@Component(modules = [AppModule::class, NetModule::class])
@Singleton
interface AppComponent {
    fun plusNewsComponent(module: NewsModule): NewsComponent
}