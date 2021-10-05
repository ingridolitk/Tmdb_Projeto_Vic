package com.ingrid.projetointegrador_vic.presentation.allmovie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ingrid.projetointegrador_vic.domain.model.ApiResult
import com.ingrid.projetointegrador_vic.domain.model.ApiResultGenre
import com.ingrid.projetointegrador_vic.domain.model.Genre
import com.ingrid.projetointegrador_vic.domain.model.MovieResult
import com.ingrid.projetointegrador_vic.domain.repository.MoviesRepository

class MovieViewModel (private val moviesRepository: MoviesRepository) : ViewModel() {

    val loadingEvent: MutableLiveData<Boolean> = MutableLiveData()
    val errorLiveData: MutableLiveData<String> = MutableLiveData()
    val movieList: MutableLiveData<List<MovieResult>> = MutableLiveData()
    val genreList: MutableLiveData<List<Genre>> = MutableLiveData()

    fun getMovies() {
        loadingEvent.value = true

        moviesRepository.getMovies { result ->
            when (result) {
                is ApiResult.Success -> {
                    movieList.value = result.movies

                }
                is ApiResult.ServerError -> {
                    errorLiveData.value = result.message
                }
            }
            loadingEvent.value = false
        }
    }

    fun getGenre() {
        loadingEvent.value = true

        moviesRepository.getGenre { result ->
            when (result) {
                is ApiResultGenre.Success -> {
                    genreList.value = result.genre
                }
                is ApiResultGenre.ServerError -> {
                    errorLiveData.value = result.message

                    loadingEvent.value = false
                }
            }
        }
    }

    class ViewModelFactory(private val repository: MoviesRepository) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
                return MovieViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
