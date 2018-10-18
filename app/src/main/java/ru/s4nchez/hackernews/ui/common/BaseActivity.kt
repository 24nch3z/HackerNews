package ru.s4nchez.hackernews.ui.common

import android.os.Bundle
import android.support.v4.app.Fragment
import com.arellomobile.mvp.MvpAppCompatActivity
import ru.s4nchez.hackernews.R

abstract class BaseActivity : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    fun setFragment(fragment: Fragment, addToBackStackFlag: Boolean) {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        if (addToBackStackFlag) transaction.addToBackStack(null)
        transaction.commit()
    }
}
