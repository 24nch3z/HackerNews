package ru.s4nchez.hackernews.ui

import android.os.Bundle
import ru.s4nchez.hackernews.ui.common.BaseActivity
import ru.s4nchez.hackernews.ui.news.NewsView

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            setFragment(NewsView.newInstance(), false)
        }
    }
}
