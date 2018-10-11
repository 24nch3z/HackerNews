package ru.s4nchez.hackernews.ui.list

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_list.*
import ru.s4nchez.hackernews.App
import ru.s4nchez.hackernews.R
import ru.s4nchez.hackernews.data.entities.Item
import ru.s4nchez.hackernews.ui.common.BaseFragment
import javax.inject.Inject

class ListView : BaseFragment(), ContractView {

    override val layout = R.layout.fragment_list
    private var adapter: ItemAdapter? = null

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

    override fun initAdapter(items: ArrayList<Item>) {
        adapter = ItemAdapter(items)
        recycler_view.adapter = adapter
    }
}