package ar.com.wolox.android.example.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class News(
    val id: String,
    @SerializedName("userId")
    val userId: String,
    @SerializedName("createdAt")
    val createdAt: String,
    val title: String,
    val picture: String,
    val text: String,
    val likes: ArrayList<String>
) : Serializable {

    var isFavorite = false
}