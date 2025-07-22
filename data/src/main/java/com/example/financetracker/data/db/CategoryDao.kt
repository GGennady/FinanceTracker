package com.example.financetracker.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.financetracker.data.db.entities.CategoryEntity

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category_table")
    suspend fun getAll(): List<CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategories(categories: List<CategoryEntity>)
}