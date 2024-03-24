package com.example.flixster

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var show = intent.getSerializableExtra("show") as? Show
        if (show != null) {
            val tvDetailName: TextView = findViewById(R.id.tvDetailName)
            val ivDetailPoster: ImageView = findViewById(R.id.ivDetailPoster)
            val tvDetailOverview: TextView = findViewById(R.id.tvDetailOverview)
            val tvDetailPopularity: TextView = findViewById(R.id.tvDetailPopularity)
            val tvDetailVoteAverage: TextView = findViewById(R.id.tvDetailVoteAverage)
            val tvDetailVoteCount: TextView = findViewById(R.id.tvDetailVoteCount)

            tvDetailName.text = show.name
            tvDetailOverview.text = show.overview
            tvDetailVoteAverage.text = "Vote Average: ${show.voteAverage}"
            tvDetailVoteCount.text = "Vote Count: ${show.voteCount}"
            tvDetailPopularity.text = "Popularity: ${show.popularity}"
            Glide.with(this)
                .load(show.posterImageUrl)
                .transform(RoundedCorners(150))
                .into(ivDetailPoster)

        }

    }
}