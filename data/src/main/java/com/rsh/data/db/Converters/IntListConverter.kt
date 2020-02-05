package com.rsh.data.db.Converters

import androidx.room.TypeConverter

class IntListConverter {

    @TypeConverter
    fun fsaveIntList(intList: List<Int>):String{
        return intList.joinToString()
    }

    @TypeConverter
    fun restoreIntList(data: String):List<Int>{
        return data.split(", ").toList().map { Integer.parseInt(it)}
    }

}