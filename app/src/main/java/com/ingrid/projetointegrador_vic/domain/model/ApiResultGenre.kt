package com.ingrid.projetointegrador_vic.domain.model

open class ApiResultGenre {

    class Success(val genre: List<Genre>?) : ApiResultGenre()
    class ServerError (val message: String? = null) : ApiResultGenre()
}