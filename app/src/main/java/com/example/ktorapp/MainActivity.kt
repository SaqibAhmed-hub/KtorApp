package com.example.ktorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ktorapp.repository.MovieApi
import io.ktor.util.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val movieAdapter = MovieAdapter()

    @OptIn(KtorExperimentalAPI::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val linearlayout = LinearLayoutManager(this)
        linearlayout.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearlayout
        recyclerView.adapter = movieAdapter
        callAPI()

    }

    @KtorExperimentalAPI
    private fun callAPI() {
        CoroutineScope(Dispatchers.IO).launch {
            val movieAPI = MovieApi()
            val response = movieAPI.getAllMovies()
            withContext(Dispatchers.Main){
                movieAdapter.setMovieItem(response)
            }
        }
    }
}