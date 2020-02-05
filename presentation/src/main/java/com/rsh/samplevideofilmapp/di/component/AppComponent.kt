package com.rsh.samplevideofilmapp.di.component

import android.app.Application
import com.rsh.samplevideofilmapp.App
import com.rsh.samplevideofilmapp.di.module.*
import com.rsh.samplevideofilmapp.di.scope.AppScope
import com.rsh.samplevideofilmapp.di.subcomponent.DetailSubcomponet
import com.rsh.samplevideofilmapp.di.subcomponent.MainActivitySubComponent
import com.rsh.samplevideofilmapp.di.subcomponent.MainListSubCompinent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@AppScope
@Component(
    modules = [
        NetworkModule::class,
        AppModule::class,
        RepositoryModule::class,
        FilmsUseCaseModule::class,
        ViewModelFactoryModule::class,
        SharedPrefModule::class
    ]
)
interface AppComponent {

    fun getMainListSubComponent(): MainListSubCompinent
    fun getDetailSubComponent(): DetailSubcomponet
    fun getMainActivitySubComponent(): MainActivitySubComponent


}

