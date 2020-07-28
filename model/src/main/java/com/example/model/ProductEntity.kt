package com.example.model

data class ProductListDTO(
    val products : List<ProductDTO>
)


data class ProductDTO(
    val id : Int,
    val url : String,
    val name : String,
    val description : String,
    val price : Double
)


class ProductModel(
    val id : Int,
    val url : String,
    val name : String
    // val description : String,
    // val price : Double
)