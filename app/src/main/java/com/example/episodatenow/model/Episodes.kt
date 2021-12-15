package com.example.episodatenow.model

import com.google.gson.annotations.SerializedName

data class Episodes(

    @SerializedName("season" )
    var season : Int? = null,

    @SerializedName("episode" )
    var episode : Int? = null,

    @SerializedName("name" )
    var name : String? = null,

    @SerializedName("air_date" )
    var airDate : String? = null

)
