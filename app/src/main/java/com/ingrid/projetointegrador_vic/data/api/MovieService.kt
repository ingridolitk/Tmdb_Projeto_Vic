package com.ingrid.projetointegrador_vic.data.api

import com.ingrid.projetointegrador_vic.domain.model.GenreResponse
import com.ingrid.projetointegrador_vic.domain.model.MovieResult
import retrofit2.Call
import retrofit2.http.GET

interface MovieService {

    @GET("saga")
    fun returnMovies(): Call<List<MovieResult>>

    @GET("genre/movie/list?api_key=f7eaeb09d0bc520ae20d604476749d60")
    fun returnGenres(): Call<GenreResponse>
}
