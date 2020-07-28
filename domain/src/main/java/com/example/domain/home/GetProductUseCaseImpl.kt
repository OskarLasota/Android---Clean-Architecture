package com.example.domain.home


import com.example.data.repo.home.ProductRepository
import com.example.model.ProductDTO
import io.reactivex.Single
import javax.inject.Inject

class GetProductUseCaseImpl @Inject constructor(var repo: ProductRepository) : GetProductUseCase{

    override fun getProducts() : Single<List<ProductDTO>> {
        return repo.getProducts()
    }

    override suspend fun getCoroutineProducts(): List<ProductDTO> {
        return repo.getCoroutinesProducts()
    }


}