package com.ingrid.projetointegrador_vic.domain.repository

import com.ingrid.projetointegrador_vic.domain.model.ApiResult
import com.ingrid.projetointegrador_vic.domain.model.ApiResultGenre

interface MoviesRepository {
    fun getMovies(usersResultCallback: (result: ApiResult) -> Unit)
    fun getGenre(usersResultCallback: (result: ApiResultGenre) -> Unit)
}