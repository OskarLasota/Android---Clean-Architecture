package com.example.cleanarchitecture.common.di

import android.content.Context
import com.example.cleanarchitecture.common.App
import com.example.data.common.scheduler.AppScheduler
import com.example.cleanarchitecture.home.HomeActivity
import com.example.cleanarchitecture.home.di.HomeModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import io.reactivex.disposables.CompositeDisposable
import com.example.data.common.scheduler.SchedulerWrapper

@Module
abstract class AppModule {

    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun contributeHomeActivity(): HomeActivity

    @Binds
    abstract fun bindContext(app: App): Context

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideScheduler(): AppScheduler {
            return SchedulerWrapper.getInstance()
        }


        @JvmStatic
        @Provides
        fun provideCompositeDisposable(): CompositeDisposable {
            return CompositeDisposable()
        }
    }

}