package ru.s4nchez.hackernews.interactors

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class ObservableInteractor<T> {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    abstract fun buildObservableInteractor(): Observable<T>

    fun execute(observer: DisposableObserver<T>) {
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