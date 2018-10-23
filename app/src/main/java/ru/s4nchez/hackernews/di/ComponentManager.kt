package ru.s4nchez.hackernews.di

import android.content.Context
import ru.s4nchez.hackernews.di.component.AppComponent
import ru.s4nchez.hackernews.di.component.DaggerAppComponent
import ru.s4nchez.hackernews.di.component.NewsComponent
import ru.s4nchez.hackernews.di.module.AppModule
import ru.s4nchez.hackernews.di.module.NewsModule

class ComponentManager(context: Context) {

    val dagger: AppComponent = buildAppComponent(context)

    private var newsComponent: NewsComponent? = null

    private fun buildAppComponent(context: Context): AppComponent {
        return DaggerAppComponent.builder()
                .appModule(AppModule(context))
                .build()
    }

    fun plusNewsComponent(): NewsComponent =
            newsComponent
                    ?: dagger.plusNewsComponent(NewsModule())
                            .also { newsComponent = it }

    fun destroyNewsComponent() {
        newsComponent = null
    }
}