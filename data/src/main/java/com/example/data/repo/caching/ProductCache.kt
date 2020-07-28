package com.example.data.repo.caching

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="product_table")
class ProductCache {

    @PrimaryKey var id = 0
    var url : String = ""
    var name : String = ""


}