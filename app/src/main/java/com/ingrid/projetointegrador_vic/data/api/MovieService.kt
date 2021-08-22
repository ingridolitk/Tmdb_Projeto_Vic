package com.ingrid.projetointegrador_vic.data.api

import com.ingrid.projetointegrador_vic.domain.model.GenreResponse
import com.ingrid.projetointegrador_vic.domain.model.Movie
import com.ingrid.projetointegrador_vic.domain.model.MovieResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/top_rated?api_key=f7eaeb09d0bc520ae20d604476749d60")
    fun returnMovies(): Call<MovieResult>

    @GET("genre/movie/list?api_key=f7eaeb09d0bc520ae20d604476749d60")
    fun returnGenres(): Call<GenreResponse>

   // @Query("SELECT * FROM Movie WHERE title = :title")
    //fun returnTitle (title: String): Movie
}
