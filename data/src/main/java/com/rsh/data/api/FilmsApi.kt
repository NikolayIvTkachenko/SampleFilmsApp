package com.rsh.data.api

import com.rsh.domain.model.FilmsResponse
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmsApi {
    //http://api.themoviedb.org/3/movie/popular?api_key=befc7872520fd736c58948abb2f4a53c&page=2
    //http://api.themoviedb.org/3/movie/popular?api_key=befc7872520fd736c58948abb2f4a53c
    @GET("/3/movie/popular")
    fun getFilmList(@Query("api_key") api_key: String, @Query("page") page: Int):
            Single<FilmsResponse>




}