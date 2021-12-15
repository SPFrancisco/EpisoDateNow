package com.example.episodatenow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.episodatenow.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EpisoDateViewModel() : ViewModel() {

    private val _tvShowList = MutableLiveData<List<TvShow>>()
    private val _tvShow = MutableLiveData<TvShow>()
    private val _tvShowEpisodesList = MutableLiveData<List<Episodes>>()
    private val _exception = MutableLiveData<Throwable>()
    private val _selected : MutableLiveData<TvShow> = MutableLiveData()
    private val _pagination : MutableLiveData<Pagination> = MutableLiveData()
    private val _loading : MutableLiveData<Boolean> = MutableLiveData()

    val tvShowList : LiveData<List<TvShow>> = _tvShowList
    val tvShow : LiveData<TvShow> = _tvShow
    val tvShowEpisodesList : LiveData<List<Episodes>> = _tvShowEpisodesList
    val selected : LiveData<TvShow> = _selected
    val exception : LiveData<Throwable> = _exception
    val pagination : LiveData<Pagination> = _pagination
    val loading : LiveData<Boolean> = _loading

    val service = EpisoDateService.getInstance()

    fun loadTvShows(page: Int = 1) {
        _loading.value = true
        var response = service.getMostPopular(page).enqueue(object : Callback<EpisoDateApiResponse> {
            override fun onResponse(
                call: Call<EpisoDateApiResponse>,
                response: Response<EpisoDateApiResponse>
            ) {
                val responseBody = response.body()
                _tvShowList.value = responseBody!!.tvShows
                _pagination.value = Pagination(
                    responseBody.page,
                    responseBody.total,
                    responseBody.pages
                )
                _loading.value = false
            }

            override fun onFailure(call: Call<EpisoDateApiResponse>, t: Throwable) {
                _exception.value = t
                _loading.value = false
            }

        })
    }

    fun select(tvShow: TvShow) {
        _selected.value = tvShow
       /* CoroutineScope(Dispatchers.IO).launch {
            val response = service.getTvShowDetails(tvShow.name.toString()).execute()
            val responseBody = response.body()
            _tvShowDetais.value = responseBody
        }*/
    }

    fun search(searchText: String) {
        _loading.value = true
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.search(searchText).execute()
            val responseBody = response.body()
            _tvShowList.postValue(responseBody!!.tvShows)
            _pagination.postValue(Pagination(
                responseBody.page,
                responseBody.total,
                responseBody.pages
            ))
            _loading.postValue(false)
        }
    }

    fun loadTvShowDetails(tvShowId: String) {

        /*CoroutineScope(Dispatchers.IO).launch {
            val response = service.getTvShowDetails(tvShowId).execute()
            val responseBody = response.body()
            _tvShow.postValue(responseBody!!.tvShow)

        }*/
        var response = service.getTvShowDetails(tvShowId).enqueue(object : Callback<EpisoDateApiDetailsResponse> {
            override fun onResponse(
                call: Call<EpisoDateApiDetailsResponse>,
                response: Response<EpisoDateApiDetailsResponse>
            ) {
                val responseBody = response.body()
                _tvShow.value = responseBody!!.tvShow
                _tvShowEpisodesList.value = responseBody!!.tvShow.episodes
            }

            override fun onFailure(call: Call<EpisoDateApiDetailsResponse>, t: Throwable) {
                _exception.value = t
            }

        })
    }
}