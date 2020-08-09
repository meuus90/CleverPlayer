package com.network.clever.di.module.fragment

import com.network.clever.presentation.stream.PlayerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PlayerFragmentModule {
    @ContributesAndroidInjector
    internal abstract fun contributePlayerFragment(): PlayerFragment
}