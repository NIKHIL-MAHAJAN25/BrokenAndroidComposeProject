package com.greedygame.brokenandroidcomposeproject.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.greedygame.brokenandroidcomposeproject.ViewModel.ApiViewModel


@Composable
fun DetailScreen(articleId: Int,
                 viewModel: ApiViewModel,
                 onBack: () -> Unit) {
    val article by viewModel.getArticle(articleId).collectAsState(initial = null)

    Column(modifier = Modifier.padding(16.dp)) {
        if (article != null) {
            Text(text = "Title: ${article!!.title}")
            Text(text = "\nAuthor: ${article!!.author}")
            Text(text = "\nContent: ${article!!.content}")
            Text(text = "\nimageurl: ${article!!.urlToImage}")

        } else {
            Text(text = "Loading...")
        }
    }
}