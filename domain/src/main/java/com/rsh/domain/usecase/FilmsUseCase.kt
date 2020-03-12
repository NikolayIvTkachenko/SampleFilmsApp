package com.rsh.domain.usecase


import com.rsh.domain.model.Films
import com.rsh.domain.model.FilmsResponse
import com.rsh.domain.repositoryInterface.I_RepositoryDbFilms
import com.rsh.domain.repositoryInterface.I_RepositoryNetworkFilms
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Callable




class FilmsUseCase(private val repositoryNetworkFilms: I_RepositoryNetworkFilms, private val reposotoryDbFilms: I_RepositoryDbFilms) {


    fun getFilmsFronNetworkFilms(page:Int):Observable<FilmsResponse> {
        val dataFilms = repositoryNetworkFilms.getFilmsList(page)
            .subscribeOn(Schedulers.io())
            .map { it ->
                it
            }.flatMapObservable { it ->
                if (it.total_pages > 1) {
                    Observable.range(1, it.total_pages)
                } else {
                    Observable.empty()
                }
            }.flatMapSingle {

                repositoryNetworkFilms.getFilmsList(it)
            }.map { it ->
                it
            }.
                doOnError{it -> it.message}

        return dataFilms
    }


    fun getSeveToDbOneFilm(obj: Films):Single<Long>{
        return reposotoryDbFilms.insertFilmSingle(obj)
            .subscribeOn(Schedulers.io())
    }


    fun getAllRecordsFilmsFlowable():Flowable<List<Films>>{
        return reposotoryDbFilms.getAllRecordsFilmsFlowable()
    }


    fun getRepositoryDb():I_RepositoryDbFilms{
        return reposotoryDbFilms
    }

    fun getFilmById(id: Int):Single<Films>{
        return reposotoryDbFilms.getFilmByIdSingle(id)
            .subscribeOn(Schedulers.io())
    }

}

