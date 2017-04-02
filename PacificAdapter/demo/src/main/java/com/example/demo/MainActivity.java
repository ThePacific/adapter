package com.example.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.demo.entity.Item;
import com.pacific.adapter.AdapterUtil;
import com.pacific.adapter.RecyclerAdapter;
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
    }

    private void loadItem() {
        ArrayList<Item> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(new Item("item index is : " + i));
        }
        adapter.addAll(AdapterUtil.toRecyclerItems(list));
    }
}
