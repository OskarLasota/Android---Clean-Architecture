package com.example.data.repo.home.service


import com.example.model.ProductDTO
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface ProductService {

    @GET("/api/products.php")
    fun getProducts(): Single<List<ProductDTO>>

    @GET("/api/products.php")
    suspend fun getCoroutineProducts() : Response<List<ProductDTO>>

}