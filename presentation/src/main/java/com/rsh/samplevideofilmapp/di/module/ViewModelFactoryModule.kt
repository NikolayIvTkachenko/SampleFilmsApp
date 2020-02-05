package com.rsh.samplevideofilmapp.di.module

import androidx.lifecycle.ViewModelProvider
import com.rsh.samplevideofilmapp.ViewModel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}