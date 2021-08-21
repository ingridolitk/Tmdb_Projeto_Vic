package com.ingrid.projetointegrador_vic.domain.model

sealed class StatusResponse {
    class Success(val movies: List<Movie>?) : StatusResponse()
    class ServerError (val message: String? = null) : StatusResponse()
}