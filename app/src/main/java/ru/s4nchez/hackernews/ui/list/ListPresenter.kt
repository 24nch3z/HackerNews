package ru.s4nchez.hackernews.ui.list

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.s4nchez.hackernews.interactors.NewsInteractor
import javax.inject.Inject

@InjectViewState
class ListPresenter @Inject constructor(
        private val interactor: NewsInteractor
) : MvpPresenter<ContractView>(), ContractPresenter {

}