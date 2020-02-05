package com.rsh.data.util

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.rsh.domain.SHARED_PREFERS_NAME
import javax.inject.Inject

class SharedPreferencesUtils @Inject constructor(val app:Application) {

    lateinit var sharedPrefer: SharedPreferences

    init {
        sharedPrefer = app.getSharedPreferences(SHARED_PREFERS_NAME, MODE_PRIVATE)
    }

    fun getSharedPrefernces():SharedPreferences = sharedPrefer

    @SuppressLint("NewApi")
    fun addSharedPreferencesString(key: String, value: String ){
        val editor = sharedPrefer.edit()
        editor.putString(key, value)
        editor.apply()
    }

    @SuppressLint("NewApi")
    fun addSharedPreferencesInt(key: String , value: Int){
        val editor = sharedPrefer.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    @SuppressLint("NewApi")
    fun addSharedPreferencesBoolean(key: String , value: Boolean ){
        val editor = sharedPrefer.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    @SuppressLint("NewApi")
    fun addSharedPreferencesFloat(key: String , value: Float){
        val editor = sharedPrefer.edit()
        editor.putFloat(key, value)
        editor.apply()
    }

    @SuppressLint("NewApi")
    fun addSharedPreferencesLong(key: String , value: Long){
        val editor = sharedPrefer.edit()
        editor.putLong(key, value)
        editor.apply()
    }

}