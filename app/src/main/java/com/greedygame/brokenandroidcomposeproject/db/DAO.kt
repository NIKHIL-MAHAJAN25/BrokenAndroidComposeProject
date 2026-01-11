package com.greedygame.brokenandroidcomposeproject.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.greedygame.brokenandroidcomposeproject.data.Article
import kotlinx.coroutines.flow.Flow
@Dao
interface DAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarticles(articlestable:List<Article>)

    @Query("SELECT * FROM articlestable WHERE dbid = :id")
    fun getarticlesbyid(id: Int): Flow<Article>

    @Query("SELECT * FROM articlestable")
    fun getarticles():Flow<List<Article>>


}