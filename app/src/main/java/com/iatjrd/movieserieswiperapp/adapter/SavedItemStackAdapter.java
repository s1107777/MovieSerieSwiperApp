package com.iatjrd.movieserieswiperapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.iatjrd.movieserieswiperapp.R;
import com.iatjrd.movieserieswiperapp.model.SavedItem;
import com.iatjrd.movieserieswiperapp.model.Serie;

import java.util.List;

public class SavedItemStackAdapter extends RecyclerView.Adapter<SavedItemStackAdapter.ViewHolder> {
    private List<SavedItem> savedItemsList;

    public SavedItemStackAdapter(List<SavedItem> listSavedItems){ this.savedItemsList = listSavedItems;}

    @NonNull
    @Override
    public SavedItemStackAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.saveditem_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        SavedItem savedItem = savedItemsList.get(position);

        holder.name.setText(savedItem.getName());
        holder.genre.setText(savedItem.getGenre());
        holder.description.setText(savedItem.getDescription());
        holder.seasons.setText(savedItem.getSeasons());
    }

    @Override
    public int getItemCount(){
        return savedItemsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name, genre, description, seasons;

        public ViewHolder(View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.saved_name);
            genre = itemView.findViewById(R.id.saved_genre);
            description = itemView.findViewById(R.id.saved_description);
            seasons = itemView.findViewById(R.id.saved_seasons);
        }
    }

    public List<SavedItem> getSavedItemsList() {
        return savedItemsList;
    }

    public void setSavedItemsList(List<SavedItem> savedItemsList) {
        this.savedItemsList = savedItemsList;
    }
}
