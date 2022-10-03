package com.example.flixster

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieRecyclerViewAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder>() {


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titleTextView: TextView
        val movieImageView: ImageView
        val descTextView: TextView

        init {
            titleTextView = itemView.findViewById(R.id.title)
            movieImageView = itemView.findViewById(R.id.movie_cover)
            descTextView = itemView.findViewById(R.id.desc)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.fragment_movie, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies.get(position)
        holder.titleTextView.text = movie.title
        holder.descTextView.text = movie.description
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w500/" + movie.movieImageUrl)
            .centerInside()
            .into(holder.movieImageView)

    }

    override fun getItemCount(): Int {
        return movies.size
    }
}