package com.rsh.samplevideofilmapp.di.module

import androidx.lifecycle.ViewModel
import com.rsh.samplevideofilmapp.ViewModel.DetailVideoViewModel
import com.rsh.samplevideofilmapp.ViewModel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DetailVideoViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(DetailVideoViewModel::class)
    internal abstract fun bindDetailVideoViewModel(viewModel: DetailVideoViewModel): ViewModel
}