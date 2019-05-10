package ar.com.wolox.android.example.ui.home.news

import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.News
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import dagger.Lazy
import javax.inject.Inject

class NewsDetailActivity @Inject constructor() : WolmoActivity() {

    // Lazy example, a lazy injection does not build the dependencies until #get() is called
    @Inject
    internal lateinit var lazyHomeFragment: Lazy<NewsDetailFragment>

    var news: News? = null

    override fun layout(): Int = R.layout.activity_base

    override fun init() {
        news = intent.getSerializableExtra("news") as News?
        lazyHomeFragment.get().news = this!!.news
        replaceFragment(R.id.vActivityBaseContent, lazyHomeFragment.get())
    }
}