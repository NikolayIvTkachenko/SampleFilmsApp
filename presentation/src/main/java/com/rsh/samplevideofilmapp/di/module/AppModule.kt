package com.rsh.samplevideofilmapp.di.module


import android.app.Application
import androidx.room.Room
import com.rsh.data.db.AppDatabase
import com.rsh.data.db.dao.FilmsDao
import com.rsh.domain.NAME_DATABASE_APP
import com.rsh.samplevideofilmapp.App
import com.rsh.samplevideofilmapp.di.scope.AppScope
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module(includes = [

])
class AppModule @Inject constructor(val app: App){

    @AppScope
    @Provides
    fun provideApp():App {
        return app
    }



    @AppScope
    @Provides
    fun getDB(app: App): AppDatabase = AppDatabase.getInstance(app.applicationContext)



    @AppScope
    @Provides
    fun provideFilmsDao(db: AppDatabase):FilmsDao = db.filmsDao()
}