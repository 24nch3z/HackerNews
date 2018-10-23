package ru.s4nchez.hackernews.interactors

import android.annotation.SuppressLint
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

abstract class Interactor<T, P> {

    abstract fun buildObservableInteractor(params: P): Single<T>

    @SuppressLint("CheckResult")
    fun execute(observer: DisposableSingleObserver<T>, params: P) {
        val observable = this.buildObservableInteractor(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        observable.subscribeWith(observer)
    }
}