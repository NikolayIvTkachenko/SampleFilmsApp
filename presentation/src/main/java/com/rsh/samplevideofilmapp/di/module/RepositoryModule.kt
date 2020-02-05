package com.rsh.samplevideofilmapp.di.module


import com.rsh.data.api.FilmsApi
import com.rsh.data.db.AppDatabase
import com.rsh.data.db.dao.FilmsDao
import com.rsh.data.repository.FilmsDbRepository
import com.rsh.data.repository.FilmsNetworkRepository
import com.rsh.domain.repositoryInterface.I_RepositoryDbFilms
import com.rsh.domain.repositoryInterface.I_RepositoryNetworkFilms
import com.rsh.samplevideofilmapp.di.scope.AppScope
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @AppScope
    @Provides
    fun provideFilmNetworkRepository(api: FilmsApi) : I_RepositoryNetworkFilms {
       return FilmsNetworkRepository(api)
    }

    @AppScope
    @Provides
    fun provideFilmDbRepository(filmDao: FilmsDao) : I_RepositoryDbFilms {
        return FilmsDbRepository(filmDao)
    }

}