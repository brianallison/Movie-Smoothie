package com.mobilepgh.movie_smoothie.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mobilepgh.movie_smoothie.R;
import com.mobilepgh.movie_smoothie.entities.Movie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private static final String TAG = "MovieAdapter";

    private ArrayList<Movie> movies;
    private Context context;
    private MovieAdapterClickHandler clickHandler;
    private String baseURL = "http://image.tmdb.org/t/p/w185";

    public MovieAdapter(ArrayList<Movie> movies, Context context, MovieAdapterClickHandler handler) {
        this.movies = movies;
        this.clickHandler = handler;
        this.context = context;
    }

    public interface MovieAdapterClickHandler{
        void onClick(String movieDetails);
    }

    //place view holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.poster_grid_item,
                viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //insert data in viewholder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Glide.with(context)
                //.asBitmap()
                .load(baseURL + movies.get(i).getPosterPath())
                .into(viewHolder.poster);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView poster;
        LinearLayout parent;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setPadding(0,0,0,0);
            poster = itemView.findViewById(R.id.iv_item_number);
            parent = itemView.findViewById(R.id.ll_item_parent);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Movie movie = movies.get(getAdapterPosition());
            int id = movie.getId();
            clickHandler.onClick(Integer.toString(id));
        }
    }

    public void addMovies(ArrayList<Movie> moreMovies){
        for (Movie m : moreMovies){
            movies.add(m);
        }
        notifyDataSetChanged();
    }

    public void clearMovies(){
        movies = new ArrayList<>();
    }
}
