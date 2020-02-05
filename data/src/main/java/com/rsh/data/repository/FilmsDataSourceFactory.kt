package com.rsh.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.rsh.domain.model.Films
import com.rsh.domain.repositoryInterface.I_RepositoryDbFilms
import io.reactivex.disposables.CompositeDisposable

@Deprecated("Надо разбираться с библиотекой? как она работает с котлин")
class FilmsDataSourceFactory (private val repDb: I_RepositoryDbFilms, private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, Films>(){

    val filmsLiveDataSource = MutableLiveData<FilmsDataSource>()

    override fun create(): DataSource<Int, Films> {

        val movieDataSource = FilmsDataSource(repDb, compositeDisposable)
        filmsLiveDataSource.postValue(movieDataSource)

        return movieDataSource
    }
}