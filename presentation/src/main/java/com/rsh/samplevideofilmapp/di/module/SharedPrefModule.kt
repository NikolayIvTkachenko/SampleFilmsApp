package com.rsh.samplevideofilmapp.di.module

import android.app.Application
import com.rsh.data.api.FilmsApi
import com.rsh.data.repository.FilmsNetworkRepository
import com.rsh.data.util.SharedPreferencesUtils
import com.rsh.samplevideofilmapp.App
import com.rsh.samplevideofilmapp.di.scope.AppScope
import dagger.Module
import dagger.Provides

@Module
class SharedPrefModule {
    @AppScope
    @Provides
    fun provideSharedPrefernces(app: App) = SharedPreferencesUtils(app)
}