package com.example.domain.home


import com.example.data.repo.home.service.ProductService
import com.example.model.ProductDTO
import io.reactivex.Single

interface GetProductUseCase {

    fun getProducts() : Single<List<ProductDTO>>
    suspend fun getCoroutineProducts() : List<ProductDTO>
}