package com.pacific.app.adapter.item

import com.pacific.adapter3.AdapterViewHolder
import com.pacific.adapter3.SimpleRecyclerItem
import com.pacific.app.adapter.R
import com.pacific.app.adapter.databinding.ItemMovieBinding

class MovieItem(val data: Movie) : SimpleRecyclerItem() {

    override fun getLayout(): Int {
        return R.layout.item_movie
    }

    override fun bind(holder: AdapterViewHolder) {
        val binding = holder.binding(ItemMovieBinding::bind)
        binding.itemPosterTitle.text = data.name
        binding.itemPosterRunningTime.text = data.showTime
        holder.attachImageLoader(R.id.item_poster_post)
    }
}