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
import com.rsh.samplevideofilmapp.App

import com.rsh.samplevideofilmapp.R
import com.rsh.samplevideofilmapp.View.Adapters.FilmPagedListAdapter
import com.rsh.samplevideofilmapp.ViewModel.VideoListViewModel
import com.rsh.samplevideofilmapp.ViewModel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list_movie.*
import javax.inject.Inject


class VideoListFragment : BaseFragment() {


    private lateinit var videoListViewModel: VideoListViewModel
    private lateinit var filmAdapter: FilmPagedListAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun getLayoutById(): Int {
        return R.layout.fragment_list_movie
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        Log.d("LIFECYCLE", "onAttach")
        App.getAppComponent().getMainListSubComponent().inject(this)
        videoListViewModel =
            ViewModelProviders.of(this, viewModelFactory)[VideoListViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("LIFECYCLE", "onCreate")


        getDataFromDbOrNetworkMethods()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("LIFECYCLE", "onViewCreated")
        initUI(view)
    }

    fun initUI(view: View) {
        setupView(view)
        getFilmsListData()
    }

    fun getDataFromDbOrNetworkMethods() {
        videoListViewModel.getDataGeneralSouirce()
    }

    fun setupView(view: View) {
        filmAdapter = FilmPagedListAdapter(App.getContextApp())
        val gridLayoutManager = GridLayoutManager(App.getContextApp(), 3)

        rv_movie_list.layoutManager = gridLayoutManager
        rv_movie_list.setHasFixedSize(true)
        rv_movie_list.adapter = filmAdapter
    }

    fun getFilmsListData() {
        Log.d("LIFECYCLE", "getFilmsListData()")

        videoListViewModel.getListFilmsFromDB().observe(viewLifecycleOwner, Observer {
            filmAdapter.submitList(it)
        })

        videoListViewModel.getProgessBarOnOff().observe(viewLifecycleOwner, Observer {
            if (it) {
                data_video.visibility = View.GONE
                progress_network_update_bar?.visibility = View.VISIBLE
            } else {
                progress_network_update_bar?.visibility = View.GONE
                data_video.visibility = View.VISIBLE
            }
        })




        videoListViewModel.getProgressBarupdate().observe(viewLifecycleOwner, Observer {
            Log.d("LIFECYCLE", " it = " + it)
            var procent = it ?: 0
            tv_bar_wait?.text =
                App.getContextApp().getString(R.string.downloaded_main) + " " + procent + " %"
        })

    }


    override fun onStart() {
        super.onStart()
        Log.d("LIFECYCLE", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LIFECYCLE", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LIFECYCLE", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LIFECYCLE", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LIFECYCLE", "onDestroy")

    }
}
