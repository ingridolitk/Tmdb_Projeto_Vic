package com.ingrid.projetointegrador_vic.domain.model

data class Genre  (

    val id: Int,
    val name: String

)
data class GenreResponse(
    val genres: List<Genre>
)