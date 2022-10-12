package com.example.ktorapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ktorapp.data.Movie
import kotlinx.android.synthetic.main.card_view.view.*

class MovieAdapter : RecyclerView.Adapter<MyViewHolder>() {

    private var movieList = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = movieList[position]
        holder.category.text = movie.category
        Glide.with(holder.itemView.context)
            .load(movie.imageUrl)
            .into(holder.image)
        holder.name.text = movie.name
        holder.desc.text = movie.desc

    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setMovieItem(movieList: List<Movie>) {
        this.movieList = movieList.toMutableList()
        notifyDataSetChanged()
    }

}

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val image: AppCompatImageView = view.movie_image
    val name: AppCompatTextView = view.movie_name
    val category: AppCompatTextView = view.movie_category
    val desc: AppCompatTextView = view.movie_desc
}
