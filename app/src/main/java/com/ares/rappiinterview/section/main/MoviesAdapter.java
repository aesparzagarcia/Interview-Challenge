package com.ares.rappiinterview.section.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ares.rappiinterview.BuildConfig;
import com.ares.rappiinterview.R;
import com.ares.rappiinterview.data.model.Movie;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by ares on 2019-05-28
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>{

    private List<Movie.results> movieList;
    private iMovieClickListener listener;
    private Context context;

    public MoviesAdapter(List<Movie.results> movieList, iMovieClickListener listener, Context context) {
        this.movieList = movieList;
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);

        return new MoviesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        Glide.with(context)
                .load(BuildConfig.BASEURL_IMG + movieList.get(position).poster_path)
                .into(holder.imageView);

        holder.imageView.setOnClickListener(v -> listener.onMovieClick(movieList.get(position)));

    }

    public void setMovies(List<Movie.results> movie){
        this.movieList = movie;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class MoviesViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.movie_poster);
        }
    }
}
