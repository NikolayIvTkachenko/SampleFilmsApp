package com.rsh.domain.repositoryInterface

import androidx.paging.DataSource
import com.rsh.domain.model.Films
import io.reactivex.Flowable
import io.reactivex.Single

interface I_RepositoryDbFilms {
    fun insertListFilms(listFilms: List<Films>):List<Long>

    fun insertListFilmsSample(listFilms: List<Films>):List<Long>

    fun insertFilmSingle(item: Films):Single<Long>

    fun getAllRecordsFilms():List<Films>

    fun getAllRecordsFilmsFlowable(): Flowable<List<Films>>

    fun getAllFilmsDataSource(): DataSource.Factory<Int, Films>

    //fun getAllRecordsFilmsLiveData():LiveData<List<Films>>

    fun getFilmByIdSingle(id: Int):Single<Films>
}