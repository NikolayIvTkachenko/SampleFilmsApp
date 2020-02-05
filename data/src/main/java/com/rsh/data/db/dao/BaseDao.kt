package com.rsh.data.db.dao

import androidx.room.*

abstract class BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(obj: T):Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(objList: List<T>):List<Long>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun update(obj: T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun update(obj: List<T>)

    @Transaction
    open fun insertOrUpdate(obj: T){
        val id = insert(obj)
        if(id == -1L) update(obj)
    }

    @Transaction
    open fun insertOrUpdate(objList: List<T>){
        val insertResult = insert(objList)
        val updateList = mutableListOf<T>()

        for (i in insertResult.indices){
            if(insertResult[i] == -1L)updateList.add(objList[i])
        }

        if (updateList.isNotEmpty()) update(updateList)
    }

    @Delete
    abstract fun delete(obj: T)


}