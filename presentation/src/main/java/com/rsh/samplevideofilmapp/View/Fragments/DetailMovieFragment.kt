package com.rsh.samplevideofilmapp.View.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.rsh.domain.BASE_URL_POSTER
import com.rsh.domain.ID_FILM
import com.rsh.domain.model.Films
import com.rsh.samplevideofilmapp.App

import com.rsh.samplevideofilmapp.R
import com.rsh.samplevideofilmapp.View.Adapters.FilmPagedListAdapter
import com.rsh.samplevideofilmapp.ViewModel.DetailVideoViewModel
import com.rsh.samplevideofilmapp.ViewModel.VideoListViewModel
import com.rsh.samplevideofilmapp.ViewModel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_detail_movie.*
import kotlinx.android.synthetic.main.fragment_list_movie.*
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject


class DetailMovieFragment : BaseFragment() {


    private lateinit var detailVideoViewModel: DetailVideoViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun getLayoutById(): Int {
        return R.layout.fragment_detail_movie
    }

    private var idFilm : Int = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.getAppComponent().getDetailSubComponent().inject(this)

        arguments?.let {
            idFilm = it.getInt(ID_FILM,0)!!
            Log.d("ARGUM_FRAGMENT", " id = "+ idFilm)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI(view)
    }

    fun initUI(view: View) {
        getFilmData()
    }


    fun getFilmData(){
        Log.d("ARGUM_FRAGMENT", " getFilmData()" )
        Log.d("ARGUM_FRAGMENT", " id = "+ idFilm)
        detailVideoViewModel = ViewModelProviders.of(this, viewModelFactory)[DetailVideoViewModel::class.java]
        detailVideoViewModel.getFilmById(idFilm)
        detailVideoViewModel.getMutableLiveDataFilm().observe(this, Observer {
            bindUI(it)
        })
    }

    fun bindUI(it: Films){
        film_title.text = it.title
        film_release_date.text = it.release_date
        film_overview.text = it.overview

        val moviePosterURL = BASE_URL_POSTER + it.poster_path
        Glide.with(this)
            .load(moviePosterURL)
            .into(iv_film_poster);
    }
}
