package com.example.ailist.data

import kotlinx.coroutines.flow.Flow

class ItemRepository(private val itemDao: ItemDao) {
    val allItems: Flow<List<ItemEntity>> = itemDao.getAll()

    suspend fun insert(item: ItemEntity) {
        itemDao.insert(item)
    }

    suspend fun updateDone(itemId: Int, done: Boolean) {
        itemDao.setDone(itemId, done)
    }

    suspend fun delete(item: ItemEntity) {
        itemDao.delete(item)
    }
}
