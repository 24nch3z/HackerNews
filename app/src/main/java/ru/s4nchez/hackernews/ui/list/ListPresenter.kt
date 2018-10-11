package ru.s4nchez.hackernews.ui.list

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.s4nchez.hackernews.interactors.NewsInteractor
import javax.inject.Inject

@InjectViewState
class ListPresenter @Inject constructor(
        private val interactor: NewsInteractor
) : MvpPresenter<ContractView>(), ContractPresenter {

    private val ids = ArrayList<Long>()

    init {
        loadIds()
    }

    fun loadIds() {
        interactor.getNewStories().enqueue(object : Callback<List<Long>> {
            override fun onFailure(call: Call<List<Long>>, t: Throwable) {}

            override fun onResponse(call: Call<List<Long>>, response: Response<List<Long>>) {
                Log.d("", "")
            }
        })
    }
}