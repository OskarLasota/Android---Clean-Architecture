package com.example.cleanarchitecture.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cleanarchitecture.R
import com.example.data.repo.caching.ProductCache
import com.example.model.ProductDTO
import com.example.model.ProductModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HomeContract.View {

    @Inject
    lateinit var presenter: HomeContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.getCache()

    }

    override fun onPause() {
        presenter.onPause()
        super.onPause()
    }

    override fun showError(message: String) {

    }

    override fun initCoroutineView(data: List<ProductDTO>) {
        println("init coroutine view")
    }

    override fun initView(data: List<ProductModel>) {
        println("initialize view")
        println(data.size)
        //if data.size() > 0 then store in cache
        if(data.isNotEmpty()){
            val productCacheList = ArrayList<ProductCache>()

            for (productModel in data) {
                var cached = ProductCache()
                cached.id = productModel.id
                cached.name = productModel.name
                cached.url = productModel.url
                productCacheList.add(cached)
            }
            presenter.populateCache(productCacheList as List<ProductCache>)
        }
    }

    override fun initCacheView(data: List<ProductCache>) {
        //if data.size() == 0 OR data.date is older than 2 days then call api
        if(data.isEmpty()){
            presenter.init()
        }else{
            //initialize view
            println("cache not empty")
            println("data size is ${data.size}")
        }
    }


}