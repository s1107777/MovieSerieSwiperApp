package com.iatjrd.movieserieswiperapp;

import androidx.recyclerview.widget.DiffUtil;
import java.util.List;

public class CardStackCallback extends DiffUtil.Callback {
    private List<ItemModel> old, baru;

    public CardStackCallback(List<ItemModel> old, List<ItemModel> baru) {
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
        return old.get(oldItemPosition).getMovieImage() == baru.get(newItemPosition).getMovieImage();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return old.get(oldItemPosition) == baru.get(newItemPosition);
    }
}
