package com.example.data.repo.home

import com.example.model.ProductDTO
import com.example.model.ProductModel
import io.reactivex.functions.Function

import javax.inject.Inject

class ProductToModelMapper
@Inject constructor() : Function<List<ProductDTO>, List<ProductModel>> {
    override fun apply(t: List<ProductDTO>): List<ProductModel> {
        val articleModelList = ArrayList<ProductModel>()

        for (articleDTO in t) {
            articleModelList.add(ProductModel(articleDTO.id, articleDTO.url, articleDTO.name))
        }

        return articleModelList
    }

}