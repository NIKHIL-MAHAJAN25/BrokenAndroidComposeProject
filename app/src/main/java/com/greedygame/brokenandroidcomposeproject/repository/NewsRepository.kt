package com.greedygame.brokenandroidcomposeproject.repository

import com.greedygame.brokenandroidcomposeproject.BuildConfig
import com.greedygame.brokenandroidcomposeproject.data.Article
import com.greedygame.brokenandroidcomposeproject.db.AppDatabase
import com.greedygame.brokenandroidcomposeproject.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepository (private val database: AppDatabase)
{

    val articles = database.DAO().getarticles()
    suspend fun refreshNews() {
        withContext(Dispatchers.IO) {
            val response = ApiClient.api.getArticles(BuildConfig.News)
            if (response.articles.isNotEmpty()) {
                database.DAO().insertarticles(response.articles)
            }
        }
    }
    fun getArticleById(id: Int) = database.DAO().getarticlesbyid(id)

}