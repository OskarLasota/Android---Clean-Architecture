package com.example.domain.caching

import com.example.data.repo.caching.CachingRepository
import com.example.data.repo.caching.ProductCache
import javax.inject.Inject

class GetCachedProductsUseCaseImpl @Inject constructor(var repo: CachingRepository) :
    GetCachedProductsUseCase {

    override suspend fun getProducts() : List<ProductCache> {
        return repo.getCachedProducts()
    }

    override suspend fun insertProduct(productCache: ProductCache) {
        repo.insertProduct(productCache)
    }


}