package com.example.episodatenow.model

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface EpisoDateService {

    @GET("/api/most-popular")
    fun getMostPopular(@Query("page")page : Int = 1) : Call<EpisoDateApiResponse>

    @GET("/api/search")
    fun search(@Query("q") searchText : String) : Call<EpisoDateApiResponse>

    @GET("/api/show-details")
    fun getTvShowDetails(@Query("q") show: String) : Call<EpisoDateApiDetailsResponse> //Response<TvShowDetails>

    companion object {

        private var _instance : EpisoDateService? = null

        fun getInstance(): EpisoDateService {

            if(_instance == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://www.episodate.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(OkHttpClient.Builder().build())
                    .build()

                _instance = retrofit.create(EpisoDateService::class.java)
            }


            return _instance!!
        }


    }
}