package ru.s4nchez.hackernews.ui.list

import android.annotation.SuppressLint
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.s4nchez.hackernews.data.entities.Item
import ru.s4nchez.hackernews.interactors.NewsInteractor
import javax.inject.Inject

@InjectViewState
class ListPresenter @Inject constructor(
        private val interactor: NewsInteractor
) : MvpPresenter<ContractView>(), ContractPresenter {

    private val ids = ArrayList<Int>()
    private val news = ArrayList<Item>()
    private var isLoading = false

    init {
        if (!isLoading && ids.isEmpty()) loadIds()
    }

    @SuppressLint("CheckResult")
    fun loadNew(id: Int) {
        interactor.getItem(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                            news.add(it)
                            viewState.initAdapter(news)
                        }, {
                            Log.e("", it.message)
                        }
                )
    }

    @SuppressLint("CheckResult")
    fun loadIds() {
        interactor.getNewStories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                            ids.addAll(it)
                            loadNew(it[0])
                        }, {
                            Log.e("", it.message)
                        }
                )
    }
}