package com.rsh.data.db.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rsh.domain.model.Films
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
abstract class FilmsDao : BaseDao<Films>(){

    @Query("SELECT * FROM films")
    abstract fun getFilmsList():List<Films>

    @Query("SELECT * FROM films")
    abstract fun getFilmsListFlowable(): Flowable<List<Films>>

    @Query("SELECT * FROM films")
    abstract fun getFilmsListLiveData(): LiveData<List<Films>>


    @Query("SELECT * FROM films WHERE Id = :id")
    abstract fun getFilmById(id: Int): Films

    @Query("SELECT * FROM films WHERE Id = :id")
    abstract fun getFilmByIdSingle(id: Int): Single<Films>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertList(objList: List<Films>):List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertFilmSingle(item: Films):Single<Long>


    @Query("SELECT * FROM films")
    abstract fun getAllFilmsDataSource(): DataSource.Factory<Int, Films>

}