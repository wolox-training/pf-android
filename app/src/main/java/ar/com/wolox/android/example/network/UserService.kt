package ar.com.wolox.android.example.network

import ar.com.wolox.android.example.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET("/users")
    fun getUserByMail(@Query("email") email: String): Call<List<User>>
}
