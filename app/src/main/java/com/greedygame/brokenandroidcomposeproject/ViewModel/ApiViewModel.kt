package com.greedygame.brokenandroidcomposeproject.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greedygame.brokenandroidcomposeproject.BuildConfig
import com.greedygame.brokenandroidcomposeproject.data.Article
import com.greedygame.brokenandroidcomposeproject.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ApiViewModel :ViewModel()
{
    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles=_articles.asStateFlow()
    init {
        fetchnews()
    }
    fun fetchnews()
    {
        try {
            viewModelScope.launch(Dispatchers.Main) {
                val apiKey = BuildConfig.News
                val response = ApiClient.api.getArticles(apiKey)
                if (response.article.isNotEmpty()) {

                }
            }
        }catch (e:Exception)
        {
            e.printStackTrace()
        }
    }

}