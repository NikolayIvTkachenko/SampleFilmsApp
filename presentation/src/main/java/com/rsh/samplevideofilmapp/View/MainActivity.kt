package com.rsh.samplevideofilmapp.View

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.rsh.data.util.SharedPreferencesUtils
import com.rsh.samplevideofilmapp.App
import com.rsh.samplevideofilmapp.R
import com.rsh.samplevideofilmapp.View.Fragments.VideoListFragment
import kotlinx.android.synthetic.main.activity_main.*

import javax.inject.Inject


class MainActivity : BaseActivity() {


    var navController: NavController? = null

    @Inject
    lateinit var shared: SharedPreferencesUtils

    override fun getLayoutById() = R.layout.activity_main



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    }

    override fun initUI() {
        navController?.navigate(R.id.videoListFragment);

    }




    override fun testSetup() {
        App.getAppComponent().getMainActivitySubComponent().inject(this)
        shared.addSharedPreferencesBoolean("KEY1", true)
        shared.addSharedPreferencesString("KEY2", "TEST 777")
        test_text.text = shared.getSharedPrefernces().getString("KEY2", "")
    }
}
