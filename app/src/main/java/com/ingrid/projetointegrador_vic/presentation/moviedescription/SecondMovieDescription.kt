package com.ingrid.projetointegrador_vic.presentation.moviedescription

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.graphics.Bitmap
import android.os.Parcelable
import com.ingrid.projetointegrador_vic.R
import com.squareup.picasso.Picasso


class SecondMovieDescription : AppCompatActivity() {
    lateinit var actor: TextView
    lateinit var title: TextView
    lateinit var plot: TextView
    lateinit var genre: TextView
    lateinit var imgMovie: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_movie_description)

        actor = findViewById(R.id.txtActor)
        title = findViewById(R.id.titleMovie)
        plot = findViewById(R.id.txtPlot)
        genre = findViewById(R.id.txtGenre)
        imgMovie = findViewById(R.id.imgMovieDesc)

        val value = intent.getStringExtra("actor")
        val titleDesc = intent.getStringExtra("title")
        val plotDesc = intent.getStringExtra("plot")
        val genreDesc = intent.getStringExtra("genre")

        actor.setText(value)
        title.setText(titleDesc)
        plot.setText(plotDesc)
        genre.setText(genreDesc)

        val intent = intent
        val url = intent.getStringExtra("imgMovie")
        Picasso.get().load(url).into(imgMovie);


    }
}