package com.ingrid.projetointegrador_vic.domain.repository

import com.ingrid.projetointegrador_vic.domain.model.ApiResult

interface MoviesRepository {
    fun getMovies(usersResultCallback: (result: ApiResult) -> Unit)
}