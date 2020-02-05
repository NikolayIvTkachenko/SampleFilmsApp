package com.rsh.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import androidx.paging.toLiveData
import com.rsh.domain.START_PAGE
import com.rsh.domain.model.Films
import com.rsh.domain.repositoryInterface.I_RepositoryDbFilms
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

@Deprecated("Надо разбираться с библиотекой? как она работает с котлин")
class FilmsDataSource(private val repDb : I_RepositoryDbFilms,
                      private val compositeDisposable: CompositeDisposable)
    :PositionalDataSource<Films>(){

    private var page = START_PAGE
    val dataOperationStatus: MutableLiveData<DataOperationStatus> = MutableLiveData()


    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Films>) {
        dataOperationStatus.postValue(DataOperationStatus.START_GET_DATA_FROM_DB)

    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Films>) {
        dataOperationStatus.postValue(DataOperationStatus.START_GET_DATA_FROM_DB)



    }




}


