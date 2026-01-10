package com.greedygame.brokenandroidcomposeproject.data

import com.google.gson.Gson

data class Article(
    val id: Int,
    val title: String,
    val author: String?,
    val content: String?,
    val imageUrl: String?
)

//object BrokenRepository {
//    fun fetchArticlesBlocking(): List<Article> {
//        Thread.sleep(2000)
//        val fakeJson = "[{\"identifier\":1,\"heading\":\"Hello\",\"writer\":\"Alice\"}]"
//        val gson = Gson()
//        val articles: Array<Article> = try {
//            gson.fromJson(fakeJson, Array<Article>::class.java)
//        } catch (e: Exception) {
//            emptyArray()
//        }
//        return articles.toList()
//    }
//
//    fun updateArticle(article: Article) {
//    }
//}