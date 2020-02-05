package com.rsh.samplevideofilmapp.di.subcomponent

import com.rsh.samplevideofilmapp.View.Fragments.VideoListFragment
import com.rsh.samplevideofilmapp.View.MainActivity
import com.rsh.samplevideofilmapp.di.module.FilmsUseCaseModule
import com.rsh.samplevideofilmapp.di.module.VideoListViewModelModule
import com.rsh.samplevideofilmapp.di.module.ViewModelFactoryModule
import com.rsh.samplevideofilmapp.di.scope.FragmentScope
import dagger.Subcomponent


@FragmentScope
@Subcomponent(
    modules = [


        VideoListViewModelModule::class
    ]
)
interface MainListSubCompinent {
    fun inject(listFragment: VideoListFragment)
}