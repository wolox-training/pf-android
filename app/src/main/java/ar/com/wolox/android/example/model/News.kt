package ar.com.wolox.android.example.model

data class News(val id: String, val userId: String, val createdAt: String, val title: String, val picture: String, val text: String, val likes: ArrayList<String>) {

    var isFavorite = false
}