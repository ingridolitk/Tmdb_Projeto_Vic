package com.ingrid.projetointegrador_vic.presentation.allmovie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ingrid.projetointegrador_vic.R
import com.ingrid.projetointegrador_vic.data.repository.MoviesRepositoryImpl
import com.ingrid.projetointegrador_vic.domain.model.Movie
import com.ingrid.projetointegrador_vic.presentation.adapter.GenAdapter
import com.ingrid.projetointegrador_vic.presentation.adapter.MovieAdapter
import com.ingrid.projetointegrador_vic.utils.setVisible

class FragmentAllMovie : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var rvGenres: RecyclerView
    private lateinit var loading: ProgressBar

    private val viewModel: MovieViewModel =
        MovieViewModel.ViewModelFactory(MoviesRepositoryImpl()).create(MovieViewModel::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return inflater.inflate(
            R.layout.fragment_all_movie,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rvMovie)
        rvGenres = view.findViewById(R.id.rvGenres)
        loading = view.findViewById(R.id.loading)

        setupRecyclerView()

        setObservers()
        viewModel.getMovies()
        viewModel.getGenre()
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )

    }

    private fun setObservers() {

        viewModel.movieList.observe(viewLifecycleOwner, Observer { movieListResponse ->
            recyclerView.adapter = movieListResponse?.let { list ->
                MovieAdapter(list, clickListener = {
                    handleClick(it)
                })

            }
            viewModel.genreList.observe(viewLifecycleOwner, Observer { genreListResponse ->
                rvGenres.adapter = genreListResponse?.let { list ->
                    GenAdapter(list, clickListener = {

                    })
                }
            })

            viewModel.errorLiveData.observe(viewLifecycleOwner, Observer { message ->
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            })

            viewModel.errorLiveDataGenre.observe(viewLifecycleOwner, Observer { message ->
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            })

            viewModel.loadingEvent.observe(viewLifecycleOwner, Observer { isVisible ->
                loading.setVisible(false)
            })
        })
    }

    private fun handleClick(movie: Movie) {
        //val intent = startActivity(Intent(context, SecondMovieDescriptionActivity::class.java))
    }
}