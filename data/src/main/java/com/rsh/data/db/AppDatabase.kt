package com.rsh.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rsh.data.db.Converters.IntListConverter
import com.rsh.data.db.dao.FilmsDao
import com.rsh.domain.NAME_DATABASE_APP
import com.rsh.domain.model.Films


@Database(
    entities = [
        Films::class
    ], version = 3,  exportSchema = false
)
@TypeConverters(IntListConverter::class)
abstract class AppDatabase :RoomDatabase(){

    abstract fun filmsDao(): FilmsDao

    companion object {

        @Volatile
        private var instance:AppDatabase? = null

        fun getInstance(context: Context): AppDatabase = instance?: synchronized(this) {
            instance ?: buildAppDatabase(context).also { instance = it }
        }

        private fun buildAppDatabase(context: Context) = Room.databaseBuilder(context, AppDatabase::class.java, NAME_DATABASE_APP)
            .fallbackToDestructiveMigration()
            .build()
    }
}

