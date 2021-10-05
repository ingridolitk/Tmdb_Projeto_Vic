package com.ingrid.projetointegrador_vic.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ingrid.projetointegrador_vic.R
import com.ingrid.projetointegrador_vic.domain.model.MovieResult
import kotlinx.android.synthetic.main.activity_second_movie_description.view.*
import kotlinx.android.synthetic.main.item_rv.view.*
import kotlinx.android.synthetic.main.item_rv.view.ratingMovie
import kotlinx.android.synthetic.main.item_rv.view.titleMovie

class MovieAdapter(private val list: List<MovieResult>,
                   private val clickListener:(MovieResult)->Unit):RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val card = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_rv,
                parent,
                false)
        return MovieViewHolder(card)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val currentItem = list[position]

        if(list[position].poster !== ""){
            holder.imgMovie?.let {
                Glide.with(holder.imgMovie.context).load(
                    list[position].poster
                ).into(it)
            }
            holder.imgMovie.setOnClickListener{
                clickListener.invoke(currentItem)
            }

        }
        if (holder is MovieViewHolder)
            holder.title.text = currentItem.title
            holder.ratingMovie.text = currentItem.rated
    }


    override fun getItemCount(): Int {
        return list.size
    }

    class MovieViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgMovie: ImageView = itemView.imgMovie
        val title: TextView = itemView.titleMovie
        val ratingMovie: TextView = itemView.ratingMovie

    }
}