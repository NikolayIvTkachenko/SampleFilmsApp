package com.rsh.data.repository

import com.rsh.data.api.FilmsApi
import com.rsh.domain.API_KEY

import com.rsh.domain.model.FilmsResponse
import com.rsh.domain.repositoryInterface.I_RepositoryNetworkFilms
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class FilmsNetworkRepository @Inject constructor(val filmsApi: FilmsApi): I_RepositoryNetworkFilms {

    override fun getFilmsList(page:Int): Single<FilmsResponse>{
        return  filmsApi.getFilmList(API_KEY, page)
    }


}


