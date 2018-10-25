package ru.s4nchez.hackernews.interactors

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import ru.s4nchez.hackernews.executor.JobExecutor

abstract class Interactor<T, P> {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    abstract fun buildObservableInteractor(params: P): Single<T>

    fun execute(observer: DisposableSingleObserver<T>, params: P, executor: JobExecutor) {
        val observable = this.buildObservableInteractor(params)
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