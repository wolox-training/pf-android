package ar.com.wolox.android.example.ui.home.news

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

    fun loadNews() {
        retrofitServices.getService(NewsService::class.java).getNews().enqueue(object : Callback<List<News>> {
            override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
                if (response.body()!!.isEmpty()) {
                    toastFactory.show("There is no news to show")
                } else {
                    for (news in response.body()!!) {
                        myNews.add(news)
                    }
                    for (i in 4..12) {
                        val news = News(i.toString(), (i - 2).toString(), "2019-05-08", "Title " + i,
                                "http://bucket1.glanacion.com/anexos/fotos/70/dia-del-amigo-2236070w620.jpg", "This is the text of the " + i, ArrayList())
                        myNews.add(news)
                    }
                }
                view.onNewsLoaded(myNews)
            }

            override fun onFailure(call: Call<List<News>>, t: Throwable) {
                toastFactory.show("Error loading the news")
            }
        })
    }
}