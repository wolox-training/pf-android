package ar.com.wolox.android.example.ui.home.news

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.News
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import kotlinx.android.synthetic.main.fragment_news.*
import javax.inject.Inject

class NewsFragment @Inject constructor(private val toastFactory: ToastFactory) : WolmoFragment<NewsPresenter>(), INewsView {

    private lateinit var viewAdapter: NewsAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun layout(): Int = R.layout.fragment_news

    override fun init() {
        viewManager = LinearLayoutManager(context)
        vRecyclerNews.layoutManager = viewManager
        vFloatingActionButton.attachToRecyclerView(vRecyclerNews)
        vFloatingActionButton.setOnClickListener {
            toastFactory.show(getString(R.string.add_news))
        }
        vSwiperefresh.setOnRefreshListener {
            context?.let { presenter.loadNews(it) }
        }
        viewAdapter = NewsAdapter(presenter.myNews)
        vRecyclerNews.adapter = viewAdapter
        context?.let { presenter.loadNews(it) }
    }

    override fun onNewsLoaded(myNews: ArrayList<News>) {
        viewAdapter.setNews(myNews)
        viewAdapter.notifyDataSetChanged()
        if (vSwiperefresh.isRefreshing) {
            toastFactory.show(getString(R.string.news_updated))
            vSwiperefresh.isRefreshing = false
        }
    }
}