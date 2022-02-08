package com.ideal.mobileexcercise.di.modules

import com.ideal.mobileexcercise.services.APICalls
import com.ideal.mobileexcercise.services.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DataModule {
    @Singleton
    @Provides
    fun providesRepository(apiCalls: APICalls): Repository =
        Repository(apiCalls)
}