package ar.com.wolox.android.example.ui.home.news

import android.net.Uri
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.News
import kotlinx.android.synthetic.main.item_news.view.*
import org.ocpsoft.prettytime.PrettyTime
import java.text.SimpleDateFormat

class NewsAdapter(private var myNews: ArrayList<News>) : RecyclerView.Adapter<NewsAdapter.NewsHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): NewsAdapter.NewsHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_news, viewGroup, false) as View
        return NewsHolder(view)
    }

    override fun getItemCount(): Int {
        return myNews.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: NewsAdapter.NewsHolder, position: Int) {
        holder.vName.text = myNews[position].title
        holder.vDesc.text = myNews[position].text
        holder.vFavorite.setOnClickListener {
            myNews[position].isFavorite = !myNews[position].isFavorite
            holder.vFavorite.isSelected = myNews[position].isFavorite
        }
        holder.vCreatedAt.text = getTimeFromDate(myNews[position].createdAt)
        holder.vFavorite.isSelected = myNews[position].isFavorite
        holder.vAvatar.setImageURI(Uri.parse(myNews[position].picture))
    }

    fun setNews(myNews: ArrayList<News>) {
        this.myNews = myNews
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

    class NewsHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val vName = view.vTitle
        val vDesc = view.vText
        val vFavorite = view.vFavorite
        var vAvatar = view.vAvatar
        var vCreatedAt = view.vCreatedAt
    }
}