package com.greedygame.brokenandroidcomposeproject.network


import com.greedygame.brokenandroidcomposeproject.data.ArticleResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

//        @GET("/v2/everything?q=android&apiKey=demo")
//        suspend fun getArticles(): List<Map<String, Any>>
    @GET("/v2/everything?q=android")
    // safe api key call from properties
    suspend fun getArticles(
        @Query("apiKey") apiKey:String
    ): ArticleResponse
}

object ApiClient {
    val api: ApiService = Retrofit.Builder()
        .baseUrl("https://newsapi.org")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}