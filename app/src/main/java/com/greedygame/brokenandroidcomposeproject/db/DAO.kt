package com.greedygame.brokenandroidcomposeproject.db

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.greedygame.brokenandroidcomposeproject.data.Article
import kotlinx.coroutines.flow.Flow

interface DAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articlestable:List<Article>)

    @Query("SELECT * FROM articlestable")
    fun getarticles():Flow<List<Article>>

    @Update
    fun updateEntity(article: Article)
}