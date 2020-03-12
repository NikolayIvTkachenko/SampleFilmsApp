package com.rsh.samplevideofilmapp.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.rsh.data.repository.FilmsDataSourceFactory
import com.rsh.data.util.SharedPreferencesUtils
import com.rsh.domain.DISTANCE_DOWNLOAD_PAGE
import com.rsh.domain.LIMIT_PER_PAGE
import com.rsh.domain.TIME_LAST_NETWORK_UPDATE
import com.rsh.domain.TIME_READ_FROM_DB
import com.rsh.domain.model.Films
import com.rsh.domain.usecase.FilmsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.time.LocalDate
import java.util.*
import javax.inject.Inject


class VideoListViewModel @Inject constructor(private val filmsusecase: FilmsUseCase, private val sharedUtil :SharedPreferencesUtils):BaseViewModel() {

    private val filmsListMutableLiveData = MutableLiveData<List<Films>>()

    private lateinit var filmsPageList: LiveData<PagedList<Films>>

    private var progressBarOnOff = MutableLiveData<Boolean>()
    private var progressBarupdate = MutableLiveData<Int>()



    private fun getPageListFilms() {
        filmsPageList = filmsusecase.getRepositoryDb().getAllFilmsDataSource().toLiveData(pageSize = 30)
    }

    fun getListFilmsFromDB() = filmsPageList


    //Общий метод для счьтения данных
    fun getDataGeneralSouirce(){

        val timeRTeadFromServer = sharedUtil.getSharedPrefernces().getLong(TIME_LAST_NETWORK_UPDATE, 0)
        val currentTime = System.currentTimeMillis()
        Log.d("TIME_PREPARE", "timeRTeadFromServer = "+timeRTeadFromServer)
        Log.d("TIME_PREPARE", "currentTime  = "+currentTime )
        if (timeRTeadFromServer == 0L) {
            Log.d("TIME_PREPARE", "timeRTeadFromServer == 0L" )
            getPageListFilms()
            getFilmsFromNetwork()
        } else if (timeRTeadFromServer>(currentTime-(TIME_READ_FROM_DB*3600*1000))) {
            Log.d("TIME_PREPARE", "timeRTeadFromServer < currentTime" )
            getPageListFilms()
        }else{
            Log.d("TIME_PREPARE", "timeRTeadFromServer >= currentTime" )
            getPageListFilms()
            getFilmsFromNetwork()
        }

    }

    fun getFilmsFromNetwork() {
        progressBarOnOff.postValue(true)
        val disposableNetwork = filmsusecase.getFilmsFronNetworkFilms(1)
            .map { it ->

                for(item : Films in it.results){
                    filmsusecase.getSeveToDbOneFilm(item)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            //Log.d("NET_RESULT", "value insert = " + it)
                        },{
                            Log.d("NET_RESULT", "throwable insert ="+it.message)
                        })
                }
                it
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                    it ->
                Log.d("PROCENT"," it.page ="+it.page)
                Log.d("PROCENT"," it.total_pages ="+it.total_pages)
                Log.d("PROCENT"," it.page/it.total_pages ="+it.page/it.total_pages)
                var procent : Double = (it.page.toDouble()/it.total_pages.toDouble())*100

                progressBarupdate.postValue(procent.toInt())
                Log.d("PROCENT"," progressBarupdate ="+progressBarupdate.value)
                //filmsusecase.getSeveToDb(it.results)
                if (it.page ==it.total_pages){
                    //Выполняем чтение из бд
                    getPageListFilms()

                    //Сохраняем и получаем текущую дату и сохраняем в долях секунды
                    sharedUtil.addSharedPreferencesLong(TIME_LAST_NETWORK_UPDATE, System.currentTimeMillis())
                    progressBarOnOff.postValue(false)
                }
            },{
                Log.d("NET_RESULT", "throwable ="+it.message)
            },{

            },{

            })
        compositeDisposable.add(disposableNetwork)
    }

    //выполняе чтение из БД
    fun readFromDbAllRecords(){
        val disposableDB = filmsusecase.getAllRecordsFilmsFlowable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                 it ->
                    Log.d("NET_RESULT", "get from db")
                    Log.d("NET_RESULT", "check count in db, it.site = "+it.size)

            },{
                Log.d("NET_RESULT", "throwable ="+it.message)
            },{

            })
        compositeDisposable.add(disposableDB)
    }


    fun getProgessBarOnOff() = progressBarOnOff

    fun getProgressBarupdate() = progressBarupdate

    fun getLiveFilmsList() = filmsListMutableLiveData

    fun listIsEmpty():Boolean {
        return filmsPageList.value?.isEmpty() ?: true
    }


}
