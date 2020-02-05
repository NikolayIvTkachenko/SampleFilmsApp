package com.rsh.samplevideofilmapp.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract  class BaseActivity : AppCompatActivity() {


    abstract  fun getLayoutById(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutById())
        testSetup()
        initUI()
    }


    abstract fun initUI()

    abstract fun testSetup()

}