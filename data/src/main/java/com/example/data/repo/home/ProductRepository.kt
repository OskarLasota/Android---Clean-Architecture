package com.example.data.repo.home

import com.example.data.repo.home.service.ProductService
import com.example.model.ProductDTO
import io.reactivex.Single
import javax.inject.Inject

class ProductRepository @Inject constructor(private val service : ProductService) {

    fun getProducts() : Single<List<ProductDTO>> {
        return Single.defer{
            return@defer service.getProducts()
        }
    }

    suspend fun getCoroutinesProducts() :List<ProductDTO>{
        val result = service.getCoroutineProducts()
        return result.body()!!
    }

}