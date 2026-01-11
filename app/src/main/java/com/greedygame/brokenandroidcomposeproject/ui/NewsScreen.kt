package com.greedygame.brokenandroidcomposeproject.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.viewModelFactory
import com.greedygame.brokenandroidcomposeproject.NewsStates
import com.greedygame.brokenandroidcomposeproject.ViewModel.ApiViewModel
import com.greedygame.brokenandroidcomposeproject.data.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@Composable
fun NewsScreen(viewModel: ApiViewModel) {
    val articles by viewModel.articles.collectAsState()
    val state by viewModel.uistate.collectAsState()
    when (state) {
        is NewsStates.Loading -> {
            CircularProgressIndicator()
        }

        is NewsStates.Error -> {
            val msg = (state as NewsStates.Error).message
            Text(text = msg)
        }

        is NewsStates.Sucess -> {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(articles) { article ->
                    androidx.compose.material3.Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        androidx.compose.foundation.layout.Row(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                        ) {
                            androidx.compose.material3.Icon(
                                imageVector = androidx.compose.material.icons.Icons.Default.Person,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(60.dp)
                                    .padding(end = 16.dp)
                            )
                            Column {
                                Text(text = "Title: ${article.title ?: "No title"}")
                                Text(text = "Author: ${article.author ?: "No author"}")

                            }

                        }

                    }
                }

            }
        }
    }
}
    // remember is waste coz forces ui to call network each time screen is rotated
//    var articles by remember { mutableStateOf<List<Article>>(emptyList()) }
//    var loading by remember { mutableStateOf(true) }
//    var error by remember { mutableStateOf<String?>(null) }

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


//    if (loading) {
//        CircularProgressIndicator()
//        return
//    }
//
//
//    if (error != null) {
//        Text(text = "Error: $error")
//        return
//    }
//
//
//    if (articles.isEmpty()) {
//        Text(text = "No articles found")
//        return
//    }
//
//    LazyColumn(modifier = Modifier.fillMaxSize()) {
//        items(articles) { article ->
//            Column {
//                Text(article.title)
//                Text(article.author ?: "no author")
//            }
//        }
//    }
//}