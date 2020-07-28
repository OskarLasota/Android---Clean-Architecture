package com.example.data.common.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.repo.caching.ProductCache

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(schedule : ProductCache)

    @Query("DELETE FROM product_table")
    fun deleteAllProducts()

    @Query("select * from product_table")
    fun getProducts() : List<ProductCache>

    @Query("DELETE FROM product_table WHERE id = :id")
    fun removeProduct(id : Int)


}