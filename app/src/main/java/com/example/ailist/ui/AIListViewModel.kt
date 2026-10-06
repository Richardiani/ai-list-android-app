package com.example.ailist.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ailist.data.AppDatabase
import com.example.ailist.data.ItemEntity
import com.example.ailist.data.ItemRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class AIListViewModel(
    private val repository: ItemRepository
) : ViewModel() {

    private val _searchQuery = mutableStateOf("")
    var searchQuery: String
        get() = _searchQuery.value
        set(value) {
            _searchQuery.value = value
        }

    private val _selectedCategory = mutableStateOf("All")
    var selectedCategory: String
        get() = _selectedCategory.value
        set(value) {
            _selectedCategory.value = value
        }

    private val _draftText = mutableStateOf("")
    var draftText: String
        get() = _draftText.value
        set(value) {
            _draftText.value = value
        }

    val categories = listOf("All", "Work", "Personal", "Shopping", "Health", "Study")

    val smartSuggestions = mapOf(
        "Work" to listOf("Review PR", "Reply to email", "Prepare meeting notes"),
        "Personal" to listOf("Call mom", "Book dentist", "Clean room"),
        "Shopping" to listOf("Buy groceries", "Order printer ink", "Check pantry"),
        "Health" to listOf("Drink water", "Walk 20 minutes", "Stretch"),
        "Study" to listOf("Read chapter 3", "Review notes", "Practice quiz")
    )

    val allItems: StateFlow<List<ItemEntity>> = repository.allItems
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val filteredItems: StateFlow<List<ItemEntity>> = combine(allItems, _searchQuery, _selectedCategory) { items, query, category ->
        items.filter { item ->
            val matchesQuery = query.isBlank() || item.title.contains(query, ignoreCase = true)
            val matchesCategory = category == "All" || item.category == category
            matchesQuery && matchesCategory
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addItem() {
        val text = draftText.trim()
        if (text.isEmpty()) return

        val category = selectedCategory.takeIf { it != "All" } ?: inferCategory(text)
        val item = ItemEntity(title = text, category = category)
        viewModelScope.launch {
            repository.insert(item)
        }
        draftText = ""
    }

    fun addSuggestion(suggestion: String) {
        draftText = suggestion
        addItem()
    }

    fun toggleItemDone(item: ItemEntity) {
        viewModelScope.launch {
            repository.updateDone(item.id, !item.done)
        }
    }

    fun deleteItem(item: ItemEntity) {
        viewModelScope.launch {
            repository.delete(item)
        }
    }

    private fun inferCategory(title: String): String {
        val lower = title.lowercase()
        return when {
            lower.contains("email") || lower.contains("meeting") || lower.contains("report") || lower.contains("project") -> "Work"
            lower.contains("buy") || lower.contains("shop") || lower.contains("grocery") || lower.contains("milk") -> "Shopping"
            lower.contains("walk") || lower.contains("workout") || lower.contains("health") || lower.contains("exercise") || lower.contains("water") -> "Health"
            lower.contains("study") || lower.contains("read") || lower.contains("quiz") || lower.contains("notes") -> "Study"
            else -> "Personal"
        }
    }

    companion object {
        fun factory(context: android.content.Context): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    val repository = ItemRepository(
                        AppDatabase.getDatabase(context).itemDao()
                    )
                    return AIListViewModel(repository) as T
                }
            }
        }
    }
}
