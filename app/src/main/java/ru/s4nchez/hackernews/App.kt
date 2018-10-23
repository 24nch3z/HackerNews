package ru.s4nchez.hackernews

import android.app.Application
import ru.s4nchez.hackernews.di.component.AppComponent
import ru.s4nchez.hackernews.di.component.DaggerAppComponent
import ru.s4nchez.hackernews.di.component.NewsComponent
import ru.s4nchez.hackernews.di.module.AppModule
import ru.s4nchez.hackernews.di.module.NewsModule
import timber.log.Timber
import timber.log.Timber.DebugTree

class App : Application() {

    companion object {
        lateinit var dagger: AppComponent
        val TIMBER_TAG = "sssss"
    }

    private var newsComponent: NewsComponent? = null

    override fun onCreate() {
        super.onCreate()

        dagger = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
            Timber.tag(TIMBER_TAG)
        }
    }

    fun plusNewsComponent(): NewsComponent =
        newsComponent
                ?: dagger.plusNewsComponent(NewsModule())
                        .also { newsComponent = it }

    fun destroyNewsComponent() {
        newsComponent = null
    }
}