package com.example.demo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.demo.databinding.FragmentListViewBinding;
import com.pacific.adapter.AbsAdapter;
import com.pacific.adapter.AdapterUtil;
import com.pacific.adapter.Item;
import com.pacific.adapter.ViewHolder;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ListViewFragment extends RxFragment {

    FragmentListViewBinding binding;
    AbsAdapter adapter;
    Toast toast;

    public ListViewFragment() {
    }

    public static ListViewFragment newInstance() {
        ListViewFragment fragment = new ListViewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        adapter = new AbsAdapter(3);
        adapter.setImageLoader((iv, holder) -> {
            Item item = holder.getItem();
            if (item instanceof Cartoon) {
                Glide.with(this)
                        .load(((Cartoon) item).imageUrl())
                        .fitCenter()
                        .into(iv);
            } else if (item instanceof Cartoon_2) {
                Glide.with(this)
                        .load(((Cartoon_2) item).imageUrl())
                        .fitCenter()
                        .crossFade()
                        .into(iv);
            } else if (item instanceof Cartoon_1) {
                Glide.with(this)
                        .load(((Cartoon_1) item).imageUrl())
                        .fitCenter()
                        .into(iv);
            }
        });
        adapter.setOnClickListener(v -> {
            ViewHolder holder = AdapterUtil.getHolder(v);
            switch (holder.getItem().getLayout()) {
                case R.layout.item_cartoon:
                    break;
                case R.layout.item_cartoon_1:
                    break;
                case R.layout.item_cartoon_2:
                    break;
                default:
                    break;
            }
            toast(holder.getCurrentPosition());
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_view, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.lvCartoon.setAdapter(adapter);
        fetch();
    }

    void fetch() {
        Observable.just("http://i.imgur.com/")
                .subscribeOn(Schedulers.newThread())
                .map(base -> {
                    String ext = ".jpg";
                    String[] imageUrls = {
                            base + "CqmBjo5" + ext, base + "zkaAooq" + ext, base + "0gqnEaY" + ext,
                            base + "9gbQ7YR" + ext, base + "aFhEEby" + ext, base + "0E2tgV7" + ext,
                            base + "P5JLfjk" + ext, base + "nz67a4F" + ext, base + "dFH34N5" + ext,
                            base + "FI49ftb" + ext, base + "DvpvklR" + ext, base + "DNKnbG8" + ext,
                            base + "yAdbrLp" + ext, base + "55w5Km7" + ext, base + "NIwNTMR" + ext,
                            base + "DAl0KB8" + ext, base + "xZLIYFV" + ext, base + "HvTyeh3" + ext,
                            base + "Ig9oHCM" + ext, base + "7GUv9qa" + ext, base + "i5vXmXp" + ext,
                            base + "glyvuXg" + ext, base + "u6JF6JZ" + ext, base + "ExwR7ap" + ext,
                            base + "Q54zMKT" + ext, base + "9t6hLbm" + ext, base + "F8n3Ic6" + ext,
                            base + "P5ZRSvT" + ext, base + "jbemFzr" + ext, base + "8B7haIK" + ext,
                            base + "aSeTYQr" + ext, base + "OKvWoTh" + ext, base + "zD3gT4Z" + ext,
                            base + "z77CaIt" + ext
                    };
                    List<Item> items = new ArrayList<>();
                    for (int i = 0; i < imageUrls.length; i++) {
                        if (i % 3 == 0) {
                            items.add(Cartoon.builder()
                                    .name("Type1, position:" + i)
                                    .description("Use Java 8 language features")
                                    .imageUrl(imageUrls[i])
                                    .build());
                        } else if (i % 3 == 1) {
                            items.add(Cartoon_1.builder()
                                    .name("Type2, position:" + i)
                                    .description("Use Java 8 language features")
                                    .imageUrl(imageUrls[i])
                                    .build());
                        } else {
                            items.add(Cartoon_2.builder()
                                    .name("Type3, position:" + i)
                                    .description("Use Java 8 language features")
                                    .imageUrl(imageUrls[i])
                                    .build());
                        }
                    }
                    return items;
                })
                .compose(this.bindUntilEvent(FragmentEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(items -> adapter.addAll(items));
    }

    void toast(int index) {
        String text = "position:" + index;
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
        toast.show();
    }
}