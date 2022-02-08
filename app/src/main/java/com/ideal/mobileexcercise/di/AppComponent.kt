package com.ideal.mobileexcercise.di

import android.app.Application
import com.ideal.mobileexcercise.App
import com.ideal.mobileexcercise.di.modules.AppModules
import com.ideal.mobileexcercise.di.modules.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModules::class,
        MainActivityModule::class
    ]
)

interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: App)
}
