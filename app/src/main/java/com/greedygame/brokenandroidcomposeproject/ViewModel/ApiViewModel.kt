package com.greedygame.brokenandroidcomposeproject.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greedygame.brokenandroidcomposeproject.BuildConfig
import com.greedygame.brokenandroidcomposeproject.NewsStates
import com.greedygame.brokenandroidcomposeproject.data.Article
import com.greedygame.brokenandroidcomposeproject.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ApiViewModel :ViewModel()
{
    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles=_articles.asStateFlow()
    private val _uistate: MutableStateFlow<NewsStates> = MutableStateFlow(NewsStates.Loading)
    val uistate:StateFlow<NewsStates> = _uistate.asStateFlow()
    init {
        fetchnews()
    }
    fun fetchnews()
    {
        try {
            viewModelScope.launch(Dispatchers.Main) {
                val apiKey = BuildConfig.News
                val response = ApiClient.api.getArticles(apiKey)
                if (response.articles.isNotEmpty()) {
                    _articles.value=response.articles
                    _uistate.value=NewsStates.Sucess(article = response.articles)
                }else{
                    _uistate.value=NewsStates.Error("No news found")
                }
            }
        }catch (e:Exception)
        {
            _uistate.value = NewsStates.Error(e.message ?: "Unknown Error")
            e.printStackTrace()
        }
    }

}