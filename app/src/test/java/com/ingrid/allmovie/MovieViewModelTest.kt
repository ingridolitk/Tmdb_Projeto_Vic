package com.ingrid.allmovie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ingrid.projetointegrador_vic.domain.model.StatusResponse
import com.ingrid.projetointegrador_vic.domain.model.StatusResponseGenre
import com.ingrid.projetointegrador_vic.domain.model.Genre
import com.ingrid.projetointegrador_vic.domain.model.Movie
import com.ingrid.projetointegrador_vic.domain.repository.MoviesRepository
import com.ingrid.projetointegrador_vic.presentation.allmovie.MovieViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UsersViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieListObserver: Observer<List<Movie>>

    @Mock
    private lateinit var genreListObserver: Observer<List<Genre>>

    private lateinit var viewModel: MovieViewModel

    @Test
    fun `when viewModel getMovie result in success then sets movieListLiveData`() {
        //Arrange
        val list = listOf<Movie>(
            Movie(
                adult = false,
                backdrop_path = "",
                genre_ids = listOf(1, 2),
                id = 3,
                original_language = "",
                original_title = "",
                overview = "",
                popularity = 1.0,
                poster_path = "",
                release_date = "",
                title = "",
                video = false,
                vote_average = 3.0,
                vote_count = 1
            )
        )
        val listGenre = listOf<Genre>(Genre(
            id = 1,
            name = ""
        ))

        val resultSuccess = MockRepository(StatusResponse.Success(list), StatusResponseGenre.Success(listGenre))
        viewModel = MovieViewModel(resultSuccess)
        viewModel.movieList.observeForever(movieListObserver)
        viewModel.genreList.observeForever(genreListObserver)

        //Act
        viewModel.getMovies()
        viewModel.getGenre()

        // Assert
        verify(movieListObserver).onChanged(list)
        verify(genreListObserver).onChanged(listGenre)
    }
}

class MockRepository(private val result: StatusResponse, private val resultGenre: StatusResponseGenre) :
    MoviesRepository {
    override fun getMovies(usersResultCallback: (result: StatusResponse) -> Unit) {
        usersResultCallback(result)
    }

    override fun getGenre(usersResultCallback: (result: StatusResponseGenre) -> Unit) {
        usersResultCallback(resultGenre)
    }

}
