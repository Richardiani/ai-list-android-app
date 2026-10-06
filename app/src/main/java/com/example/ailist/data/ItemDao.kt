package com.example.ailist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Query("SELECT * FROM items ORDER BY done ASC, createdAt DESC")
    fun getAll(): Flow<List<ItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ItemEntity)

    @Query("UPDATE items SET done = :done WHERE id = :id")
    suspend fun setDone(id: Int, done: Boolean)

    @Delete
    suspend fun delete(item: ItemEntity)
}
