package com.greedygame.brokenandroidcomposeproject.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.greedygame.brokenandroidcomposeproject.data.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@Composable
fun NewsScreen() {
    var articles by remember { mutableStateOf<List<Article>>(emptyList()) }
    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

//    LaunchedEffect(Unit) {
//        GlobalScope.launch(Dispatchers.Main) {
//            try {
//                val result = BrokenRepository.fetchArticlesBlocking()
//                articles = result
//                loading = false
//            } catch (e: Exception) {
//                error = e.message
//                loading = false
//            }
//        }
//    }


    if (loading) {
        CircularProgressIndicator()
        return
    }


    if (error != null) {
        Text(text = "Error: $error")
        return
    }


    if (articles.isEmpty()) {
        Text(text = "No articles found")
        return
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(articles) { article ->
            Column {
                Text(article.title)
                Text(article.author ?: "no author")
            }
        }
    }
}