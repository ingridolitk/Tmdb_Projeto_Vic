package com.ingrid.projetointegrador_vic.data.repository

import com.ingrid.projetointegrador_vic.data.api.MovieService
import com.ingrid.projetointegrador_vic.domain.model.*
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

    override fun getMovies(resultCallback: (result: ApiResult) -> Unit) {
        service.returnMovies().enqueue(object : Callback<MovieResult?> {

            override fun onResponse(
                call: Call<MovieResult?>,
                response: Response<MovieResult?>,
            ) {
                if (response.isSuccessful()) {
                    resultCallback(ApiResult.Success(response.body()?.results))
                }
            }

            override fun onFailure(call: Call<MovieResult?>, t: Throwable) {

                resultCallback(ApiResult.ServerError("Erro no sistema. Alguém me desconfigurou!"))
            }

        })
    }
    override fun getGenre(resultCallback: (result: ApiResultGenre) -> Unit) {
        service.returnGenres().enqueue(object : Callback<GenreResponse?> {

            override fun onResponse(
                call: Call<GenreResponse?>,
                response: Response<GenreResponse?>
            ) {
                if (response.isSuccessful()) {
                    resultCallback(ApiResultGenre.Success(response.body()?.genres))

                }
            }

            override fun onFailure(call: Call<GenreResponse?>, t: Throwable) {
                resultCallback(ApiResultGenre.ServerError("Erro no sistema. Alguém me desconfigurou!"))
            }

        })
    }
}