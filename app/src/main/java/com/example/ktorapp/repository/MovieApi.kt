package com.example.ktorapp.repository

import android.util.Log
import com.example.ktorapp.data.Movie
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.util.*

class MovieApi {

     private val client = HttpClient {
        install(DefaultRequest) { headers.append("Content-Type", "application/json") }
        install(JsonFeature) { serializer = GsonSerializer() }
    }


    @KtorExperimentalAPI
    suspend fun getAllMovies():List<Movie>{
        return try {
            client.get{ url("https://howtodoandroid.com/movielist.json") }
        }catch (e:Exception){
            Log.i("MovieAPI:",e.toString())
            emptyList()
        }
    }
}