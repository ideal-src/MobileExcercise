package com.ideal.mobileexcercise.di.modules

import com.ideal.mobileexcercise.ui.fragments.PhotosFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributePhotosFragment(): PhotosFragment
}