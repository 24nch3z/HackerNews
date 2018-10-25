package ru.s4nchez.hackernews.ui.news

import android.arch.paging.PagedList
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_list.*
import ru.s4nchez.hackernews.App
import ru.s4nchez.hackernews.R
import ru.s4nchez.hackernews.data.entities.NewsItem
import ru.s4nchez.hackernews.ui.common.BaseFragment
import ru.s4nchez.hackernews.utils.openUrl
import ru.s4nchez.hackernews.utils.visibilityByFlag
import javax.inject.Inject

class NewsView : BaseFragment(), ContractView, NewsAdapter.OnItemClickListener {

    override val layout = R.layout.fragment_list
    private var adapter: PagingAdapter? = null

    companion object {
        fun newInstance() = NewsView()
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: NewsPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        val app: App = activity!!.application as App
        app.componentManager.plusNewsComponent().inject(this)
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            presenter.loadIds()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val app: App = activity!!.application as App
        app.componentManager.destroyNewsComponent()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PagingAdapter(this)
        recycler_view.adapter = adapter
        recycler_view.addItemDecoration(ItemDecoration(resources
                .getDimension(R.dimen.recycler_view_spacing).toInt()))
    }

    override fun submitList(items: PagedList<NewsItem>) {
        adapter?.submitList(items)
    }

    override fun showHideProgressBar(flag: Boolean) {
        progress_bar.visibilityByFlag(flag)
    }

    override fun showHideEmptyListView(flag: Boolean) {
        empty_list.visibilityByFlag(flag)
    }

    override fun showToast(id: Int) {
        Toast.makeText(context!!, id, Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(newsItem: NewsItem) {
        if (newsItem.url == null) {
            showToast(R.string.no_link)
        } else {
            openUrl(newsItem.url)
        }
    }
}