package com.example.data.common.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.repo.caching.ProductCache


@Database(entities = [ProductCache::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun productDao() : ProductDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "user_database"
                    ).build()
                }
                return instance
            }
        }
    }
}