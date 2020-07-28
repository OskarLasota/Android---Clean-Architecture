package com.example.data.repo.caching

import android.app.Application
import com.example.data.common.database.AppDatabase
import com.example.data.common.database.ProductDao
import javax.inject.Inject

class CachingRepository @Inject constructor(_application : Application) {

    var productDao : ProductDao

    init{
        productDao = AppDatabase.getInstance(_application).productDao()
    }

    fun getCachedProducts() : List<ProductCache>{
        return productDao.getProducts()
    }
    fun insertProduct(productCache: ProductCache) {
        productDao.insert(productCache)
    }

}