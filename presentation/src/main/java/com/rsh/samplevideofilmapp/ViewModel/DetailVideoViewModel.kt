package com.rsh.samplevideofilmapp.ViewModel

import androidx.lifecycle.MutableLiveData
import com.rsh.domain.model.Films
import com.rsh.domain.usecase.FilmsUseCase
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailVideoViewModel @Inject constructor(private val filmsusecase: FilmsUseCase):BaseViewModel() {

    private val filmMutableLiveData = MutableLiveData<Films>()


    fun getFilmById(id: Int){
        val disposableFilmById = filmsusecase.getFilmById(id)
            .subscribe({
                filmMutableLiveData.postValue(it)
            },{

            })
        compositeDisposable.add(disposableFilmById)
    }

    fun getMutableLiveDataFilm() = filmMutableLiveData
}