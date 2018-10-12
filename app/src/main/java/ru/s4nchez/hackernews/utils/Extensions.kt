package ru.s4nchez.hackernews.utils

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun View.visibilityByFlag(flag: Boolean) {
    this.visibility = if (flag) View.VISIBLE else View.GONE
}