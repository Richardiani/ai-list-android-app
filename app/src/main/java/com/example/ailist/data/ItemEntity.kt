package com.example.ailist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class ItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val category: String,
    val done: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)
