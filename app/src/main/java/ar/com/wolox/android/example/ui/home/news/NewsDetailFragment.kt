package ar.com.wolox.android.example.ui.home.news

import android.os.Build
import android.support.annotation.RequiresApi
import android.text.method.ScrollingMovementMethod
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.News
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_news_detail.*
import org.ocpsoft.prettytime.PrettyTime
import java.text.SimpleDateFormat
import javax.inject.Inject

class NewsDetailFragment @Inject constructor() : WolmoFragment<NewsPresenter>() {

    var news: News? = null

    override fun layout(): Int = R.layout.fragment_news_detail

    @RequiresApi(Build.VERSION_CODES.O)
    override fun init() {
        vTitle.text = news!!.title
        vText.text = news!!.text
        vAvatar.setImageURI(news!!.picture)
        vFavorite.isSelected = news!!.isFavorite
        vCreatedAt.text = getTimeFromDate(news!!.createdAt)
        vText.movementMethod = ScrollingMovementMethod()
        vFavorite.setOnClickListener {
            vFavorite.isSelected = !vFavorite.isSelected
            news!!.isFavorite = vFavorite.isSelected
        }
        vBack.setOnClickListener {
            activity?.finish()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getTimeFromDate(dateString: String?): String {
        return try {
            var date = SimpleDateFormat("yyyy-MM-dd").parse(dateString?.substring(0, 10))
            return PrettyTime().format(date)
        } catch (e: Exception) {
            dateString.orEmpty()
        }
    }
}