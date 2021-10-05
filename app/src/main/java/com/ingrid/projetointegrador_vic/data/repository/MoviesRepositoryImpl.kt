package com.ingrid.projetointegrador_vic.data.repository

import com.ingrid.projetointegrador_vic.data.api.MovieService
import com.ingrid.projetointegrador_vic.domain.model.*
import com.ingrid.projetointegrador_vic.domain.repository.MoviesRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val URL_MOVIE = "https://private-b34167-rvmarvel.apiary-mock.com/"

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
        service.returnMovies().enqueue(object : Callback<List<MovieResult>?> {

            override fun onResponse(
                call: Call<List<MovieResult>?>,

                response: Response<List<MovieResult>?>,
            ) {
                if (response.isSuccessful()) {
                    resultCallback(ApiResult.Success(response.body()))
                }
            }

            override fun onFailure(call: Call<List<MovieResult>?>, t: Throwable) {

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