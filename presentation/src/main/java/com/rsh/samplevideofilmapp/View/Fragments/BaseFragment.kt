package com.rsh.samplevideofilmapp.View.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        setRetainInstance(true)

        return inflater.inflate(getLayoutById(),container,false)
    }

    abstract  fun getLayoutById(): Int

}