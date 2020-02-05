package com.rsh.domain.model

import com.google.gson.annotations.SerializedName


data class FilmsResponse(
    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val results: List<Films>,

    @SerializedName("total_pages")
    val total_pages: Int,

    @SerializedName("total_results")
    val total_results: Int
)