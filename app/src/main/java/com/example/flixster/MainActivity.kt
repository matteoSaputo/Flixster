package com.example.flixster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.widget.ContentLoadingProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private val API_KEY = "ad6651c8ba9a84e14c1a17d18c1c832a"
    private var movies: MutableList<Movie> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var moviesRv = findViewById<RecyclerView>(R.id.movie_list)
        var progressBar = findViewById<ProgressBar>(R.id.progress) as ContentLoadingProgressBar
        progressBar.isInvisible = false

        moviesRv.adapter = MovieRecyclerViewAdapter(movies)


        var client = AsyncHttpClient()

        client.get(
            "https://api.themoviedb.org/3/movie/now_playing?api_key=$API_KEY",
            object : JsonHttpResponseHandler() {
                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    response: String?,
                    throwable: Throwable?
                ) {
                    Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
                }

                override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                    progressBar.isInvisible = true
                    Toast.makeText(applicationContext, "Success", Toast.LENGTH_SHORT).show()
                    val jsonMovieArray = json.jsonObject.getJSONArray("results")//gets the 'results' json array from the json object
                    //parse data from array into movie objects (model)
                    for(i in 0 until jsonMovieArray.length()){ //iterate through jsonMovieArray
                         val newMovie = Movie(
                             jsonMovieArray.getJSONObject(i).getString("title"),
                             jsonMovieArray.getJSONObject(i).getString("poster_path"),
                             jsonMovieArray.getJSONObject(i).getString("overview"),
                             )//for each json object in json movie array create a movie object
                         movies.add(newMovie)//add each movie object to the movies mutable list
                     }
                    (moviesRv.adapter)?.notifyDataSetChanged()
                }

            }
        )
    }
}