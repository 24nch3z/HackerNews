package ru.s4nchez.hackernews

import android.app.Application
import ru.s4nchez.hackernews.di.AppComponent
import ru.s4nchez.hackernews.di.AppModule
import ru.s4nchez.hackernews.di.DaggerAppComponent
import timber.log.Timber
import timber.log.Timber.DebugTree

class App : Application() {

    companion object {
        lateinit var dagger: AppComponent
        val TIMBER_TAG = "sssss"
    }

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
}