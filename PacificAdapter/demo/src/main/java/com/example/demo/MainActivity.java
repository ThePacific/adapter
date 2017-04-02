package com.example.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.entity.Item;
import com.pacific.adapter.AdapterUtil;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.ViewHolder;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.lv);
        adapter = new RecyclerAdapter();
        recyclerView.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(this)
                        .size(2)
                        .colorResId(R.color.colorAccent)
                        .build());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        loadItem();


        adapter.addOnClickListener(R.layout.item, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get ViewHolder
                ViewHolder holder = AdapterUtil.getHolder(v);

                //find view in ItemView
                TextView textName = AdapterUtil.findView(holder.itemView, R.id.text_name);

                //get Item
                Item item = holder.getItem();

                //get adapter data size
                int size = holder.getSize();

                holder.isFirstItem();
                holder.isLastItem();

                //Note: !!!
                //GetCurrentPosition() works for ListView,GridView, ViewPager, Spinner
                //and RecyclerView, but GetAdapterPosition() only works for RecyclerView. Why?
                //Because ViewHolder extends RecyclerView.ViewHolder and RecyclerView.ViewHolder
                //has nothing to do with ListView, GridView, ViewPager and Spinner.
                //So don't use GetAdapterPosition() to get adapter position when you are using
                //ListView, GridView, ViewPager, Spinner, or you will always get 0

                int position = holder.getCurrentPosition();//works for all adapter views
                //int position = holder.getAdapterPosition();//works for RecyclerView
            }
        });
    }

    private void loadItem() {
        ArrayList<Item> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(new Item("item index is : " + i));
        }
        adapter.addAll(AdapterUtil.toRecyclerItems(list));
    }
}
