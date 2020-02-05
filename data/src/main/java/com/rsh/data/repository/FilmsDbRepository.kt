package com.rsh.data.repository

import androidx.paging.DataSource
import com.rsh.data.db.AppDatabase
import com.rsh.data.db.dao.FilmsDao
import com.rsh.domain.model.Films
import com.rsh.domain.repositoryInterface.I_RepositoryDbFilms
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class FilmsDbRepository @Inject constructor(val filmsDao: FilmsDao) : I_RepositoryDbFilms{

    override fun insertListFilms(listFilms: List<Films>):List<Long>{
        return filmsDao.insert(listFilms)
    }

    override fun insertFilmSingle(item : Films):Single<Long>{
        return filmsDao.insertFilmSingle(item)
    }

    override fun insertListFilmsSample(listFilms: List<Films>):List<Long>{
        return filmsDao.insert(listFilms)
    }

    override fun getAllRecordsFilms():List<Films> {
        return filmsDao.getFilmsList()
    }

    override fun getAllRecordsFilmsFlowable(): Flowable<List<Films>> {
        return filmsDao.getFilmsListFlowable()
    }


    override fun getAllFilmsDataSource(): DataSource.Factory<Int, Films>{
        return filmsDao.getAllFilmsDataSource()
    }

    override fun getFilmByIdSingle(id: Int):Single<Films>{
        return filmsDao.getFilmByIdSingle(id)
    }

}