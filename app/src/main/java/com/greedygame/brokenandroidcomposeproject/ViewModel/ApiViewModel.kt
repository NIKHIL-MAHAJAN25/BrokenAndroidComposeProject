package com.greedygame.brokenandroidcomposeproject.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greedygame.brokenandroidcomposeproject.BuildConfig
import com.greedygame.brokenandroidcomposeproject.NewsStates
import com.greedygame.brokenandroidcomposeproject.data.Article
import com.greedygame.brokenandroidcomposeproject.db.AppDatabase
import com.greedygame.brokenandroidcomposeproject.network.ApiClient
import com.greedygame.brokenandroidcomposeproject.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ApiViewModel(application: Application) : AndroidViewModel(application)
{
    private val repository = NewsRepository(AppDatabase.getDatabase(application))
    val articles = repository.articles

    private val _uistate: MutableStateFlow<NewsStates> = MutableStateFlow(NewsStates.Loading)
    val uistate:StateFlow<NewsStates> = _uistate.asStateFlow()
    init {

        observeDatabase()
        fetchnews()
    }
    private fun observeDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.articles.collect { list ->
                if (list.isNotEmpty()) {
                    _uistate.value = NewsStates.Sucess(list)
                } else {
                    _uistate.value = NewsStates.Loading
                }
            }
        }
    }

    fun getArticle(id: Int): kotlinx.coroutines.flow.Flow<Article> {
        return repository.getArticleById(id)
    }




    fun fetchnews() {
        viewModelScope.launch {
            try {
                repository.refreshNews()
            } catch (e: Exception) {
                if (_uistate.value is NewsStates.Loading) {
                    _uistate.value = NewsStates.Error("Network failed: ${e.message}")
                }
            }
        }
    }
}