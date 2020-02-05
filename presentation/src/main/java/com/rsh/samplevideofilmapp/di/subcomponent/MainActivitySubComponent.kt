package com.rsh.samplevideofilmapp.di.subcomponent

import com.rsh.samplevideofilmapp.View.Fragments.DetailMovieFragment
import com.rsh.samplevideofilmapp.View.MainActivity
import com.rsh.samplevideofilmapp.di.module.DetailVideoViewModelModule
import com.rsh.samplevideofilmapp.di.module.FilmsUseCaseModule
import com.rsh.samplevideofilmapp.di.module.ViewModelFactoryModule
import com.rsh.samplevideofilmapp.di.scope.AppScope
import com.rsh.samplevideofilmapp.di.scope.FragmentScope
import dagger.Subcomponent


@Subcomponent(
    modules = [

    ]
)
interface MainActivitySubComponent{
    fun inject(mainActivity: MainActivity)
}