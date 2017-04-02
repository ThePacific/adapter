package com.example.demo.entity;

import com.example.demo.R;
import com.example.demo.databinding.ItemDataBindingBinding;
import com.pacific.adapter.DefaultBinding;
import com.pacific.adapter.SimpleRecyclerItem;
import com.pacific.adapter.ViewHolder;

public class Item extends SimpleRecyclerItem {
    private String title;

    public String getTitle() {
        return title;
    }

    public Item(String title) {
        this.title = title;
    }

    @Override
    public int getLayout() {
        return R.layout.item_data_binding;
    }

    @Override
    public void bind(ViewHolder holder) {
        ItemDataBindingBinding binding = holder.binding();
        binding.textName.setText(title);
//        DefaultBinding binding = holder.binding();
//        binding.setText(R.id.text, title);
//        holder.attachOnClickListener(R.id.text);
    }
}
