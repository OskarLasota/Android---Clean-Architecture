package com.example.cleanarchitecture.home

import com.example.data.repo.caching.ProductCache
import com.example.model.ProductDTO
import com.example.model.ProductModel

interface HomeContract {
    interface View {
        fun showError(message: String)
        fun initView(data : List<ProductModel>)
        fun initCoroutineView(data : List<ProductDTO>)
        fun initCacheView(data : List<ProductCache>)
    }

    interface Presenter {
        fun getCache()
        fun populateCache(list : List<ProductCache>)
        fun init()
        fun initCoroutines()
        fun onPause()
    }
}