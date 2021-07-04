package com.iatjrd.movieserieswiperapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iatjrd.movieserieswiperapp.R;
import com.iatjrd.movieserieswiperapp.model.Serie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SerieStackAdapter extends RecyclerView.Adapter<SerieStackAdapter.ViewHolder> {
    private List<Serie> series;

    public SerieStackAdapter(List<Serie> series) {
        this.series = series;
    }

    @NonNull
    @Override
    public SerieStackAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.serie_card, parent, false);
        return new SerieStackAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SerieStackAdapter.ViewHolder holder, int position) {
        holder.setData(series.get(position));
    }

    @Override
    public int getItemCount() {
        return series.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView serieImage;
        TextView serieName, serieGenre, serieDescription, serieSeasons;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            serieImage = itemView.findViewById(R.id.serie_image);
            serieName = itemView.findViewById(R.id.serie_name);
            serieGenre = itemView.findViewById(R.id.serie_genre);
            serieDescription = itemView.findViewById(R.id.serie_description);
            serieSeasons = itemView.findViewById(R.id.serie_seasons);
        }

        void setData(Serie data) {
            Picasso.get()
                    .load(data.getSerieurl())
                    .fit()
                    .centerCrop()
                    .into(serieImage);
            serieName.setText(data.getSeriename());
            serieGenre.setText(data.getSeriegenre());
            serieDescription.setText(data.getSeriedescription());
            serieSeasons.setText(data.getSerieseasons());


        }
    }

    public List<Serie> getSeries() {
        return series;
    }

    public void setSeries(List<Serie> series) {
        this.series = series;
        notifyDataSetChanged();
    }
}
