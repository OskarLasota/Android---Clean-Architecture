package com.example.domain.caching

import com.example.data.repo.caching.ProductCache

interface GetCachedProductsUseCase {

    suspend fun getProducts() : List<ProductCache>
    suspend fun insertProduct(productCache: ProductCache)
}