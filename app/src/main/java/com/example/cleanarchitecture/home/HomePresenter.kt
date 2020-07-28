package com.example.cleanarchitecture.home

import com.example.data.common.scheduler.AppScheduler
import com.example.data.repo.caching.ProductCache
import com.example.data.repo.home.ProductToModelMapper
import com.example.domain.caching.GetCachedProductsUseCase
import com.example.domain.home.GetProductUseCase
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomePresenter @Inject constructor(var view : HomeContract.View, private var apiInteractor : GetProductUseCase, private var cacheInteractor : GetCachedProductsUseCase, private val productMapper: ProductToModelMapper,
                                        private val scheduler: AppScheduler, private val compositeDisposable : CompositeDisposable
) : HomeContract.Presenter{


    override fun getCache() {
        CoroutineScope(Dispatchers.IO).launch {
            view.initCacheView(cacheInteractor.getProducts())
        }
    }

    override fun populateCache(list : List<ProductCache>){
        CoroutineScope(Dispatchers.IO).launch {
            for(i in list){
                cacheInteractor.insertProduct(i)
            }
        }
    }

    override fun init() {
        compositeDisposable.add(apiInteractor.getProducts()
            .subscribeOn(scheduler.io())
            .map(productMapper)
            .observeOn(scheduler.mainThread())
            .subscribe({
                println("got result successfully")
                view.initView(it)
            },{
                println("got result failed " + it.message)
                view.showError(it.message ?: "defaultError")
            })
        )
    }

    override fun initCoroutines() {
        CoroutineScope(Dispatchers.IO).launch{
            val result = apiInteractor.getCoroutineProducts()
            view.initCoroutineView(result)
        }
    }


    override fun onPause() {
        compositeDisposable.clear()
    }

}