package com.rsh.domain.repositoryInterface

import com.rsh.domain.model.FilmsResponse
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

interface I_RepositoryNetworkFilms {

    fun getFilmsList(page:Int): Single<FilmsResponse>

}