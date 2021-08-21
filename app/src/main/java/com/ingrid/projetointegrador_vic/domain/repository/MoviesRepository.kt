package com.ingrid.projetointegrador_vic.domain.repository

import com.ingrid.projetointegrador_vic.domain.model.StatusResponse
import com.ingrid.projetointegrador_vic.domain.model.StatusResponseGenre

interface MoviesRepository {
    fun getMovies(usersResultCallback: (result: StatusResponse) -> Unit)
    fun getGenre(usersResultCallback: (result: StatusResponseGenre) -> Unit)
}