package com.example.flixster

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

private const val TAG = "ShowAdapter"

class ShowAdapter(private val context: Context, private val shows: List<Show>)
    : RecyclerView.Adapter<ShowAdapter.ViewHolder>() {


    var onShowClick: ((Show) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i(TAG, "onCreateViewHolder")
        val view = LayoutInflater.from(context).inflate(R.layout.item_show, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder position $position")
        val currentShow = shows[position]
        holder.bind(currentShow)

    }

        override fun getItemCount() = shows.size
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            private val ivShowPoster = itemView.findViewById<ImageView>(R.id.ivShowPoster)
            private val tvShowName = itemView.findViewById<TextView>(R.id.tvShowName)
//        private val tvOverview = itemView.findViewById<TextView>(R.id.tvOverview)

            fun bind(show: Show) {
                tvShowName.text = show.name
//            tvsOverview.text = movie.overview
//            Glide.with(context).load(movie.posterImageUrl).into(ivPoster)
                // Placeholder image resource
                val placeholderImage: Drawable =
                    ContextCompat.getDrawable(itemView.context, R.drawable.placeholder_image)!!
                // Load image with Glide
                Glide.with(itemView.context)
                    .load(show.posterImageUrl)
                    .transform(RoundedCorners(20))
                    .apply(
                        RequestOptions()
                            .placeholder(placeholderImage) // Set placeholder
                            .error(placeholderImage)
                    ) // Set error placeholder if loading fails
                    .into(ivShowPoster)
                itemView.setOnClickListener { v ->
                    val intent = Intent(v.context, DetailActivity::class.java)
                    intent.putExtra("show", show)
                    v.context.startActivity(intent)
                }
            }
        }
    }


