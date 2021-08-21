package com.ingrid.projetointegrador_vic.domain.model

open class StatusResponseGenre {

    class Success(val genre: List<Genre>?) : StatusResponseGenre()
    class ServerError (val message: String? = null) : StatusResponseGenre()
}