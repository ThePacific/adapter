package com.pacific.app.adapter.item;

import com.pacific.adapter.AdapterViewHolder;
import com.pacific.adapter.DefaultBinding;
import com.pacific.adapter.SimpleRecyclerItem;
import com.pacific.app.adapter.R;

import org.jetbrains.annotations.NotNull;

public class MovieGroupItem extends SimpleRecyclerItem {

    @NotNull
    public final String data;

    public MovieGroupItem(@NotNull String data) {
        this.data = data;
    }

    @Override
    public int getLayout() {
        return R.layout.item_movie_group;
    }

    @Override
    public void bind(@NotNull AdapterViewHolder holder) {
        DefaultBinding binding = holder.binding(DefaultBinding::new);
        binding.setText(R.id.item_movie_group, data);
    }
}
