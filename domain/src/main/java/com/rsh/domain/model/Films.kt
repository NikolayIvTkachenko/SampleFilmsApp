package com.rsh.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.sun.corba.se.impl.interceptors.InterceptorList

@Entity(tableName = "films")
data class Films (

    @SerializedName("adult")
    @ColumnInfo(name ="adult")
    val adult: Boolean?,

    @SerializedName("backdrop_path")
    @ColumnInfo(name ="backdrop_path")
    val backdrop_path: String?,

    /*@SerializedName("genre_ids")
    @TypeConverters(InterceptorList::class)
    @ColumnInfo(name ="agenre_ids")
    val genre_ids: List<Int>,*/

    @PrimaryKey()
    @SerializedName("id")
    @ColumnInfo(name ="id")
    val id: Int,

    @SerializedName("original_language")
    @ColumnInfo(name ="original_language")
    val original_language: String?,

    @SerializedName("original_title:")
    @ColumnInfo(name ="original_title")
    val original_title: String?,

    @SerializedName("overview")
    @ColumnInfo(name ="overview")
    val overview: String?,

    @SerializedName("popularity")
    @ColumnInfo(name ="popularity")
    val popularity: Double?,

    @SerializedName("poster_path")
    @ColumnInfo(name ="poster_path")
    val poster_path: String?,

    @SerializedName("release_date")
    @ColumnInfo(name ="release_date")
    val release_date: String?,

    @SerializedName("title")
    @ColumnInfo(name ="title")
    val title: String?,

    @SerializedName("video")
    @ColumnInfo(name ="video")
    val video: Boolean?,

    /*@SerializedName("vote_average")
    @ColumnInfo(name ="vote_average")
    val vote_average: Int,*/

    @SerializedName("vote_count")
    @ColumnInfo(name ="vote_count")
    val vote_count: Int?
)