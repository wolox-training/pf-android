package ar.com.wolox.android.example.ui.home.news

import android.content.Context
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.network.NewsService
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.core.util.ToastFactory
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NewsPresenter @Inject constructor(private val toastFactory: ToastFactory, private val retrofitServices: RetrofitServices) : BasePresenter<INewsView>() {

    var myNews = ArrayList<News>()

    fun loadNews(context: Context) {
        retrofitServices.getService(NewsService::class.java).getNews().enqueue(object : Callback<List<News>> {
            override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
                if (response.body()!!.isEmpty()) {
                    toastFactory.show(context.getString(R.string.no_news_to_show))
                } else {
                    for (news in response.body()!!) {
                        myNews.add(news)
                    }
                }
                view.onNewsLoaded(myNews)
            }

            override fun onFailure(call: Call<List<News>>, t: Throwable) {
                toastFactory.show(context.getString(R.string.error_loading_news))
            }
        })
    }
}