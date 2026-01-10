package com.greedygame.brokenandroidcomposeproject

import com.greedygame.brokenandroidcomposeproject.data.Article

sealed interface NewsStates{
    data object Loading:NewsStates
    data class Sucess(val article: List<Article>) : NewsStates
    data class Error(val message:String):NewsStates

}