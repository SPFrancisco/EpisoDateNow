package com.example.episodatenow.model

import com.google.gson.annotations.SerializedName

data class EpisoDateApiDetailsResponse (

    @SerializedName("tvShow" )
    var tvShow : TvShow

)
