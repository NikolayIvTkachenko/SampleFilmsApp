package com.rsh.samplevideofilmapp.di.module

import com.rsh.data.repository.FilmsDbRepository
import com.rsh.data.repository.FilmsNetworkRepository
import com.rsh.domain.repositoryInterface.I_RepositoryDbFilms
import com.rsh.domain.repositoryInterface.I_RepositoryNetworkFilms
import com.rsh.domain.usecase.FilmsUseCase
import com.rsh.samplevideofilmapp.di.scope.AppScope
import dagger.Module
import dagger.Provides

@Module
class FilmsUseCaseModule {

    @AppScope
    @Provides
    fun provideFilmsUseCase(repositoryNetwork: I_RepositoryNetworkFilms, repositoryDb: I_RepositoryDbFilms) = FilmsUseCase(repositoryNetwork, repositoryDb)
}