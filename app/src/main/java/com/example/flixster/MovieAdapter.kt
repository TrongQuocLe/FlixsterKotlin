package com.example.flixster

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

private const val TAG = "MovieAdapter"

class MovieAdapter(private val context: Context, private val movies: List<Movie>)
    : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i(TAG, "onCreateViewHolder")
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder position $position")
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount() = movies.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvOverview = itemView.findViewById<TextView>(R.id.tvOverview)

        fun bind(movie: Movie){
            tvTitle.text = movie.title
            tvOverview.text = movie.overview
//            Glide.with(context).load(movie.posterImageUrl).into(ivPoster)
            // Placeholder image resource
            val placeholderImage: Drawable = ContextCompat.getDrawable(context, R.drawable.placeholder_image)!!

            // Load image with Glide
            Glide.with(context)
                .load(movie.posterImageUrl)
                .apply(
                    RequestOptions()
                    .placeholder(placeholderImage) // Set placeholder
                    .error(placeholderImage)) // Set error placeholder if loading fails
                .into(ivPoster)
        }
    }

}