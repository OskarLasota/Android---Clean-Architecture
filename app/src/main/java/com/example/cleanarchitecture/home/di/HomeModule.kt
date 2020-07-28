package com.example.cleanarchitecture.home.di


import android.app.Application
import com.example.cleanarchitecture.common.App
import com.example.cleanarchitecture.home.HomeActivity
import com.example.cleanarchitecture.home.HomeContract
import com.example.cleanarchitecture.home.HomePresenter
import com.example.data.repo.caching.CachingRepository
import com.example.data.repo.home.ProductRepository
import com.example.domain.caching.GetCachedProductsUseCase
import com.example.domain.caching.GetCachedProductsUseCaseImpl
import com.example.domain.home.GetProductUseCase
import com.example.domain.home.GetProductUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module(includes = [HomeModule.HomeBindings::class])
class HomeModule {



    @Provides
    fun provideApiInteractor(repository: ProductRepository) : GetProductUseCaseImpl {
        return GetProductUseCaseImpl(repository)
    }
    @Provides
    fun provideCacheInteractor(repository: CachingRepository) : GetCachedProductsUseCaseImpl {
        return GetCachedProductsUseCaseImpl(repository)
    }
    @Provides
    fun provideApplication(application: Application) : CachingRepository {
        return CachingRepository(application)
    }


    @Module
    interface HomeBindings {

        @Binds
        fun bindApplication(app: App): Application

        @Binds
        fun bindUseCase(useCase: GetProductUseCaseImpl): GetProductUseCase

        @Binds
        fun bindCacheUseCase(useCase: GetCachedProductsUseCaseImpl): GetCachedProductsUseCase

        @Binds
        fun bindHomeView(homeActivity: HomeActivity): HomeContract.View

        @Binds
        fun bindHomePresenter(homePresenter: HomePresenter): HomeContract.Presenter
    }

}
