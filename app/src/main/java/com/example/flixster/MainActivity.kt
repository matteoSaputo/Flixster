package com.example.flixster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
        moviesRv.adapter = MovieRecyclerViewAdapter(movies)
        movies.add(Movie())

        var client = AsyncHttpClient()
  //      var params = RequestParams()
//        params["api-key"] = API_KEY


        client.get(
                "https://api.themoviedb.org/3/movie/now_playing?api_key=ad6651c8ba9a84e14c1a17d18c1c832a",
                object :  JsonHttpResponseHandler() {
                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        response: String?,
                        throwable: Throwable?
                    ) {
                        Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
                    }

                    override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
//                        val resultsJSON : JSONObject = json?.jsonObject?.get("results") as JSONObject
//                        val moviesRawJSON : String = resultsJSON.get("movies").toString()
//                        val gson = Gson()
//                        val arrayMovieType = object : TypeToken<List<Movie>>() {}.type
//
//                        movies = gson.fromJson(moviesRawJSON, arrayMovieType)
                        Toast.makeText(applicationContext, "Success", Toast.LENGTH_SHORT).show()

                    }

                }
        )
    }
}