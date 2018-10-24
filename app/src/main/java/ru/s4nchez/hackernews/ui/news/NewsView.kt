package ru.s4nchez.hackernews.ui.news

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
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
import ru.s4nchez.hackernews.utils.openUrl
import ru.s4nchez.hackernews.utils.visibilityByFlag
import javax.inject.Inject

class NewsView : BaseFragment(), ContractView, NewsAdapter.OnItemClickListener {

    override val layout = R.layout.fragment_list
    private var adapter: NewsAdapter? = null

    companion object {
        fun newInstance() = NewsView()
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: NewsPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private val recyclerViewOnScrollListener =
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val manager = recyclerView.layoutManager ?: return

                    with(manager as LinearLayoutManager) {
                        val firstVisibleItemPos = findFirstVisibleItemPosition()
                        if (childCount + firstVisibleItemPos >= itemCount && firstVisibleItemPos >= 0) {
                            presenter.loadNextPage()
                        }
                    }
                }
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        val app: App = activity!!.application as App
        app.componentManager.plusNewsComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        val app: App = activity!!.application as App
        app.componentManager.destroyNewsComponent()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(recycler_view) {
            addOnScrollListener(recyclerViewOnScrollListener)
            addItemDecoration(ItemDecoration(resources
                    .getDimension(R.dimen.recycler_view_spacing).toInt()))
        }
    }

    override fun initAdapter(items: ArrayList<Any>) {
        adapter = NewsAdapter(this)
        recycler_view.adapter = adapter
    }

    override fun updateItems(items: ArrayList<Any>) {
        adapter?.updateItems(items)
    }

    override fun showHideProgressBar(flag: Boolean) = progress_bar.visibilityByFlag(flag)

    override fun showHideEmptyListView(flag: Boolean) = empty_list.visibilityByFlag(flag)

    override fun showToast(id: Int) = Toast.makeText(context!!, id, Toast.LENGTH_SHORT).show()

    override fun onItemClick(item: Item) {
        if (item.url == null) {
            showToast(R.string.no_link)
        } else {
            openUrl(item.url)
        }
    }
}