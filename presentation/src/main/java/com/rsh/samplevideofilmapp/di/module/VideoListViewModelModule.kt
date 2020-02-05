package com.rsh.samplevideofilmapp.di.module

import androidx.lifecycle.ViewModel
import com.rsh.samplevideofilmapp.ViewModel.VideoListViewModel
import com.rsh.samplevideofilmapp.ViewModel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class VideoListViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(VideoListViewModel::class)
    internal abstract fun bindVideoListViewModell(viewModel: VideoListViewModel): ViewModel
}