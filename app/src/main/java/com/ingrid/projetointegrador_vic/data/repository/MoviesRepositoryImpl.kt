package com.ingrid.projetointegrador_vic.data.repository

import com.ingrid.projetointegrador_vic.data.api.MovieService
import com.ingrid.projetointegrador_vic.data.api.Retrofit.service
import com.ingrid.projetointegrador_vic.domain.model.*
import com.ingrid.projetointegrador_vic.domain.repository.MoviesRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MoviesRepositoryImpl: MoviesRepository {

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

                resultCallback(ApiResult.ServerError(message = "Erro de conexão"))
            }

        })
    }
}