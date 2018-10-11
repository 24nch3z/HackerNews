package ru.s4nchez.hackernews.ui.list

import com.arellomobile.mvp.MvpView
import ru.s4nchez.hackernews.data.entities.Item

interface ContractPresenter {

}

interface ContractView : MvpView {
    fun initAdapter(items: ArrayList<Item>)
}