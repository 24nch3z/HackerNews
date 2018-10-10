package ru.s4nchez.hackernews.ui.list

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import ru.s4nchez.hackernews.App
import ru.s4nchez.hackernews.R
import ru.s4nchez.hackernews.ui.common.BaseFragment
import javax.inject.Inject

class ListView : BaseFragment(), ContractView {

    override val layout = R.layout.fragment_list

    companion object {
        fun newInstance() = ListView()
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: ListPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        App.dagger.inject(this)
        super.onCreate(savedInstanceState)
    }
}