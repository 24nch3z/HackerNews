package ru.s4nchez.hackernews.ui.list

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
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

    private val recyclerViewOnScrollListener =
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    presenter.handleOnScrollListener(recyclerView.layoutManager!!)
                }
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.dagger.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.addOnScrollListener(recyclerViewOnScrollListener)
        recycler_view.addItemDecoration(ItemDecoration(resources
                .getDimension(R.dimen.recycler_view_spacing).toInt()))
    }

    override fun initAdapter(items: ArrayList<Item>) {
        adapter = ItemAdapter(items)
        recycler_view.adapter = adapter
    }

    override fun showHideProgressBar(flag: Boolean) {
        progress_bar.visibility = if (flag) View.VISIBLE else View.GONE
    }

    override fun showHideEmptyListView(flag: Boolean) {
        empty_list.visibility = if (flag) View.VISIBLE else View.GONE
    }

    override fun updateItems() {
        adapter?.updateItems()
    }

    override fun showToast(id: Int) = Toast.makeText(context!!, id, Toast.LENGTH_SHORT).show()
}