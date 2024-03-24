package com.example.flixster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import org.json.JSONException


private const val TAG = "MainActivity"
private const val NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"
private const val NOW_TRENDING_URL = "https://api.themoviedb.org/3/trending/tv/day?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"

class MainActivity : AppCompatActivity() {


    private val movies = mutableListOf<Movie>()
    private lateinit var rvMovies: RecyclerView
    private lateinit var movieAdapter: MovieAdapter

    private val shows = mutableListOf<Show>()
    private lateinit var rvShows: RecyclerView
    private lateinit var showAdapter: ShowAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvMovies = findViewById(R.id.rvMovies)
        rvShows = findViewById(R.id.rvShows)

//        val movieAdapter = MovieAdapter(this, movies)
//        rvMovies.adapter = movieAdapter
//        rvMovies.layoutManager = LinearLayoutManager(this)
//
//        val showAdapter = ShowAdapter(this, shows)
//        rvShows.adapter = showAdapter
//        rvShows.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        fetchShow()
        fetchMovie()
    }

    fun fetchMovie() {
        val movieAdapter = MovieAdapter(this, movies)
        rvMovies.adapter = movieAdapter
        rvMovies.layoutManager = LinearLayoutManager(this)
        val client = AsyncHttpClient()
        client.get(NOW_PLAYING_URL, object: JsonHttpResponseHandler(){
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "onFailure $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                Log.i(TAG, "onSuccess: JSON moovies data $json")
                try {
                    val movieJsonArray = json.jsonObject.getJSONArray("results")
                    movies.addAll(Movie.fromJsonArray(movieJsonArray)) // this line might through a JSON exception and is the reason this is going to be into a try..catch
                    movieAdapter.notifyDataSetChanged()
                    Log.i(TAG, "Movie list $movies")
                }catch (e: JSONException){
                    Log.e(TAG, "Encountered exception $e")
                }
            }
        })
    }

    fun fetchShow() {
        val showAdapter = ShowAdapter(this, shows)
        rvShows.adapter = showAdapter
        rvShows.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val client = AsyncHttpClient()
        client.get(NOW_TRENDING_URL, object: JsonHttpResponseHandler(){
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "onFailure $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                Log.i(TAG, "onSuccess: JSON shows data $json")
                try {
                    val showJsonArray = json.jsonObject.getJSONArray("results")
                    shows.addAll(Show.fromJsonArray(showJsonArray))
                    showAdapter.notifyDataSetChanged()
                    Log.i(TAG, "Show list $shows")
                }catch (e: JSONException){
                    Log.e(TAG, "Encountered exception $e")
                }
            }
        })
    }
}