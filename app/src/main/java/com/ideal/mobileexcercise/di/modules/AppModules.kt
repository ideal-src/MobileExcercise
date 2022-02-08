package com.ideal.mobileexcercise.di.modules

import com.ideal.mobileexcercise.ui.adapters.PhotosAdapter
import com.ideal.mobileexcercise.ui.view.PhotosViewModel
import dagger.Module


@Module(
    includes = [
        ViewModelModule::class,
        NetworkModule::class,
        DataModule::class
    ]
)
class AppModules