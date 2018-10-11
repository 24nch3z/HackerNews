package ru.s4nchez.hackernews

import android.app.Application
import ru.s4nchez.hackernews.di.AppComponent
import ru.s4nchez.hackernews.di.AppModule
import ru.s4nchez.hackernews.di.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var dagger: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        dagger = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}