package com.studiozeest.composeapp.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.studiozeest.composeapp.model.Problems

@Database(entities = [Problems::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

  abstract fun posterDao(): PosterDao
}
