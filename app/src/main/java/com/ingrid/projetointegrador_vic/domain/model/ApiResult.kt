package com.ingrid.projetointegrador_vic.domain.model

sealed class ApiResult {
    class Success(val movies: List<Movie>?) : ApiResult()
    class ServerError (val message: String? = null) : ApiResult()
}