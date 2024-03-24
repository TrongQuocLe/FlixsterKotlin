package com.example.flixster

import org.json.JSONArray
import java.io.Serializable

// Show class represents a movie object to be displayed in the UI
data class Show (
    val showID: Int,
    private val posterPath: String,
    val name: String,
    val overview: String,
    val popularity: Double,
    val voteAverage: Double,
    val voteCount: Double,
): Serializable {
    val posterImageUrl = "https://image.tmdb.org/t/p/w342/$posterPath"
    companion object{
        fun fromJsonArray(showJsonArray: JSONArray): List<Show>{
            val shows = mutableListOf<Show>()
            for(i in 0 until showJsonArray.length()){
                val showJson = showJsonArray.getJSONObject(i)
                shows.add(
                    Show(
                        showJson.getInt("id"),
                        showJson.getString("poster_path"),
                        showJson.getString("name"),
                        showJson.getString("overview"),
                        showJson.getDouble("popularity"),
                        showJson.getDouble("vote_average"),
                        showJson.getDouble("vote_count"),
                    )
                )
            }
            return shows
        }
    }
}