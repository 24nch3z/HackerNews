package ru.s4nchez.hackernews.interactors

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

abstract class Interactor<T> {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    abstract fun buildObservableInteractor(): Single<T>

    fun execute(observer: DisposableSingleObserver<T>) {
        val observable = this.buildObservableInteractor()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        if (compositeDisposable.isDisposed) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable.add(observable.subscribeWith(observer))
    }

    fun dispose() {
        compositeDisposable.dispose()
    }
}