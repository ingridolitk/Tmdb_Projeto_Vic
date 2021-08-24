package com.ingrid.projetointegrador_vic.data.repository

import com.ingrid.projetointegrador_vic.data.api.MovieService
import com.ingrid.projetointegrador_vic.domain.model.StatusResponse
import com.ingrid.projetointegrador_vic.domain.model.StatusResponseGenre
import com.ingrid.projetointegrador_vic.domain.model.GenreResponse
import com.ingrid.projetointegrador_vic.domain.model.MovieResponse
import com.ingrid.projetointegrador_vic.domain.repository.MoviesRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val URL_MOVIE = "https://api.themoviedb.org/3/"

class MoviesRepositoryImpl: MoviesRepository {

    var service = createRetrofit()
    private fun createRetrofit(): MovieService {
        val retrofit = Retrofit.Builder()
                .baseUrl(URL_MOVIE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(MovieService::class.java)
    }

    override fun getMovies(resultCallback: (result: StatusResponse) -> Unit) {
        service.returnMovies().enqueue(object : Callback<MovieResponse?> {

            override fun onResponse(
                    call: Call<MovieResponse?>,
                    response: Response<MovieResponse?>,
            ) {
                if (response.isSuccessful()) {
                    resultCallback(StatusResponse.Success(response.body()?.results))
                }
            }

            override fun onFailure(call: Call<MovieResponse?>, t: Throwable) {

                resultCallback(StatusResponse.ServerError(t.message))
            }

        })
    }
    override fun getGenre(resultCallback: (result: StatusResponseGenre) -> Unit) {
        service.returnGenres().enqueue(object : Callback<GenreResponse?> {

            override fun onResponse(
                    call: Call<GenreResponse?>,
                    response: Response<GenreResponse?>
            ) {
                if (response.isSuccessful()) {
                    resultCallback(StatusResponseGenre.Success(response.body()?.genres))

                }
            }

            override fun onFailure(call: Call<GenreResponse?>, t: Throwable) {
                resultCallback(StatusResponseGenre.ServerError(t.message))
            }

        })
    }

}