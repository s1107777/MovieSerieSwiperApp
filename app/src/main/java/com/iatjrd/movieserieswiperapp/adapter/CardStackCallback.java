package com.iatjrd.movieserieswiperapp.adapter;

import androidx.recyclerview.widget.DiffUtil;

import com.iatjrd.movieserieswiperapp.model.Movie;

import java.util.List;

public class CardStackCallback extends DiffUtil.Callback {
    private List<Movie> old, baru;

    public CardStackCallback(List<Movie> old, List<Movie> baru) {
        this.old = old;
        this.baru = baru;
    }

    @Override
    public int getOldListSize() {
        return old.size();
    }

    @Override
    public int getNewListSize() {
        return baru.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return old.get(oldItemPosition).getMoviename() == baru.get(newItemPosition).getMoviename();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return old.get(oldItemPosition) == baru.get(newItemPosition);
    }
}
