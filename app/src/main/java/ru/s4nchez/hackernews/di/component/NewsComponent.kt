package ru.s4nchez.hackernews.di.component

import dagger.Subcomponent
import ru.s4nchez.hackernews.di.NewsScope
import ru.s4nchez.hackernews.di.module.NewsModule
import ru.s4nchez.hackernews.ui.list.ListView

@Subcomponent(modules = [NewsModule::class])
@NewsScope
interface NewsComponent {
    fun inject(view: ListView)
}