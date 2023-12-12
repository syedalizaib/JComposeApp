package com.studiozeest.composeapp.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.studiozeest.composeapp.model.Problems

@Dao
interface PosterDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertPosterList(posters: List<Problems>)

  @Query("SELECT * FROM Problems WHERE id = :id_")
  suspend fun getPoster(id_: Long): Problems?

  @Query("SELECT * FROM Problems")
  suspend fun getPosterList(): List<Problems>
}
