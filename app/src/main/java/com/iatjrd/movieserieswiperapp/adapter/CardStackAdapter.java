package com.iatjrd.movieserieswiperapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iatjrd.movieserieswiperapp.R;
import com.iatjrd.movieserieswiperapp.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CardStackAdapter extends RecyclerView.Adapter<CardStackAdapter.ViewHolder> {

    private List<Movie> movies;

    public CardStackAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView movieImage;
        TextView movieName, movieGenre, movieDescription;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.movie_image);
            movieName = itemView.findViewById(R.id.movie_name);
            movieGenre = itemView.findViewById(R.id.movie_genre);
            movieDescription = itemView.findViewById(R.id.movie_description);
        }

        void setData(Movie data) {
            Picasso.get()
                    .load(data.getMovieurl())
                    .fit()
                    .centerCrop()
                    .into(movieImage);
            movieName.setText(data.getMoviename());
            movieGenre.setText(data.getMoviegenre());
            movieDescription.setText(data.getMoviedescription());


        }
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }
}
