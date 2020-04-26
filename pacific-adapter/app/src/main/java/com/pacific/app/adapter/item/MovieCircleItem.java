package com.pacific.app.adapter.item;

import com.pacific.adapter.SimpleRecyclerItem;
import com.pacific.adapter.AdapterViewHolder;
import com.pacific.app.adapter.R;
import com.pacific.app.adapter.databinding.ItemCircleMovieBinding;

import org.jetbrains.annotations.NotNull;

public class MovieCircleItem extends SimpleRecyclerItem {

    @NotNull
    public final Movie data;

    public MovieCircleItem(@NotNull Movie data) {
        this.data = data;
    }

    @Override
    public int getLayout() {
        return R.layout.item_circle_movie;
    }

    @Override
    public void bind(@NotNull AdapterViewHolder holder) {
        ItemCircleMovieBinding binding = holder.binding(ItemCircleMovieBinding::bind);
        binding.itemPosterTitle.setText(data.getName());
        binding.itemPosterRunningTime.setText(data.getShowTime());
        holder.attachImageLoader(R.id.item_poster_post);
    }
}
