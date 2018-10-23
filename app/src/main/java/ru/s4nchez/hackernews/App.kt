package ru.s4nchez.hackernews

import android.app.Application
import ru.s4nchez.hackernews.di.ComponentManager
import timber.log.Timber
import timber.log.Timber.DebugTree

class App : Application() {

    companion object {
        val TIMBER_TAG = "sssss"
    }

    lateinit var componentManager: ComponentManager

    override fun onCreate() {
        super.onCreate()
        componentManager = ComponentManager(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
            Timber.tag(TIMBER_TAG)
        }
    }
}