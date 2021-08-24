package com.ingrid.projetointegrador_vic.presentation.allmovie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ingrid.projetointegrador_vic.domain.model.StatusResponse
import com.ingrid.projetointegrador_vic.domain.model.StatusResponseGenre
import com.ingrid.projetointegrador_vic.domain.model.Genre
import com.ingrid.projetointegrador_vic.domain.model.Movie
import com.ingrid.projetointegrador_vic.domain.repository.MoviesRepository

class MovieViewModel (private val moviesRepository: MoviesRepository) : ViewModel() {

    val loadingEvent: MutableLiveData<Boolean> = MutableLiveData()
    val errorLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveDataGenre: MutableLiveData<String> = MutableLiveData()
    val movieList: MutableLiveData<List<Movie>> = MutableLiveData()
    val genreList: MutableLiveData<List<Genre>> = MutableLiveData()

    fun getMovies(){
        loadingEvent.value = true

        moviesRepository.getMovies { result ->
            when (result) {
                is StatusResponse.Success -> {
                    movieList.value = result.movies

                }
                is StatusResponse.ServerError -> {
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
                is StatusResponseGenre.Success -> {
                    genreList.value = result.genre
                }
                is StatusResponseGenre.ServerError -> {
                    errorLiveDataGenre.value = result.message

                }
            }
            loadingEvent.value = false
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
