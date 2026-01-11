package com.greedygame.brokenandroidcomposeproject.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.greedygame.brokenandroidcomposeproject.data.Article

@Database(entities = [Article::class], version = 1)
abstract class  AppDatabase : RoomDatabase() {
    abstract fun DAO(): DAO
    companion object{
        private var articledatabase:AppDatabase?=null
        fun getDatabase(context: Context): AppDatabase {
            return articledatabase ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "news_db"
                ).build()
                articledatabase = instance
                instance
            }
        }
    }
}