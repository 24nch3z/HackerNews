package ru.s4nchez.hackernews.utils

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun View.visibilityByFlag(flag: Boolean) {
    this.visibility = if (flag) View.VISIBLE else View.GONE
}

fun Fragment.openUrl(url: String) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)
    val packageManager: PackageManager = activity!!.packageManager;
    if (intent.resolveActivity(packageManager) != null) startActivity(intent)
}