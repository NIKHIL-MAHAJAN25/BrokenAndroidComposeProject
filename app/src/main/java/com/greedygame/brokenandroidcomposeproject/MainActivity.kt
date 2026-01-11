package com.greedygame.brokenandroidcomposeproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import com.greedygame.brokenandroidcomposeproject.ViewModel.ApiViewModel
import com.greedygame.brokenandroidcomposeproject.ui.NewsScreen

class MainActivity : ComponentActivity() {
//removinh this to prevent memory leak
//    companion object {
//        var leakedActivity: MainActivity? = null
//    }


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        leakedActivity = this not necessary
// we can also remove broken news scaffold if required
        setContent {
            Surface(modifier = Modifier.fillMaxSize())
            {
                Scaffold(topBar =
                { TopAppBar(title =
                { Text("Broken News")})})
                { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues)) {
                        NewsScreen(viewModel = ApiViewModel())
                    }
                }
//                {
//                    NewsScreen()
//                }
            }
        }
    }
}