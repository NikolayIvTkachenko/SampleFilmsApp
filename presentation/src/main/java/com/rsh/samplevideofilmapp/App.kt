package com.rsh.samplevideofilmapp

import android.app.Application
import com.rsh.samplevideofilmapp.di.component.AppComponent
import com.rsh.samplevideofilmapp.di.component.DaggerAppComponent
import com.rsh.samplevideofilmapp.di.module.AppModule
import com.rsh.samplevideofilmapp.di.module.NetworkModule
import com.rsh.samplevideofilmapp.di.module.RepositoryModule
import com.rsh.samplevideofilmapp.di.module.SharedPrefModule

class App : Application() {

    companion object {
        private lateinit var application: Application
        private lateinit var appComponent : AppComponent

        fun getContextApp():Application = application

        fun getAppComponent():AppComponent = appComponent
    }

    override fun onCreate() {
        super.onCreate()


        application = this
        appComponent = initDagger(this)
        //getAppComponent().inject(this)

    }




    private fun initDagger(app: App):AppComponent = DaggerAppComponent.builder()
        .appModule(AppModule(app))
        .sharedPrefModule(SharedPrefModule())

        .repositoryModule(RepositoryModule())
        .networkModule(NetworkModule())
        .build()


}