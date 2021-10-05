package com.ingrid.projetointegrador_vic.data.api

import com.ingrid.projetointegrador_vic.domain.model.MovieResult
import retrofit2.Call
import retrofit2.http.GET

interface MovieService {

    @GET("saga")
    fun returnMovies(): Call<List<MovieResult>>
}
