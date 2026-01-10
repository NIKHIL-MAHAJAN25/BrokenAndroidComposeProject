package com.greedygame.brokenandroidcomposeproject.data

import com.google.gson.Gson

data class Article(
   val source:Sources?,
    val author:String?,
    val title:String?,
    val content:String?,
    val urlToImage:String?
)
data class ArticleResponse(
    val article:List<Article>,
    val status:String,
    val code:String?,
    val message:String?
)
data class Sources(
    val id:String?,
    val name:String?
)
//data class Article(
//    val id: Int,
//    val title: String,
//    val author: String?,
//    val content: String?,
//    val imageUrl: String?
//)

//object BrokenRepository {
//    fun fetchArticlesBlocking(): List<Article> {
//        Thread.sleep(2000)
//        val fakeJson = "[{\"id\":1,\"title\":\"Hello\",\"author\":\"Alice\"}]"
//
////        val fakeJson = "[{\"identifier\":1,\"heading\":\"Hello\",\"writer\":\"Alice\"}]"
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