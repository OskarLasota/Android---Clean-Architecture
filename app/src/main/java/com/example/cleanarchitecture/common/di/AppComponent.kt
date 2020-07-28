package com.example.cleanarchitecture.common.di

import com.example.cleanarchitecture.api.di.ApiModule
import com.example.cleanarchitecture.common.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ApiModule::class])
interface AppComponent: AndroidInjector<App> {
    override fun inject(application: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun app(application: App): Builder

        fun build(): AppComponent
    }

}