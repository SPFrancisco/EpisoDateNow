package com.example.episodatenow.model

import com.google.gson.annotations.SerializedName

data class EpisoDateApiResponse (
    @SerializedName("total")
    var total : Int,

    @SerializedName("page")
    var page : Int,

    @SerializedName("pages")
    var pages : Int,

    @SerializedName("tv_shows")
    var tvShows : List<TvShow> = arrayListOf()
)