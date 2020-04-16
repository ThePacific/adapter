package com.pacific.app.adapter.item;

import androidx.annotation.NonNull;

public class Picture {

    @NonNull
    private final String name;

    @NonNull
    private final String url;

    public Picture(@NonNull String name, @NonNull String url) {
        this.name = name;
        this.url = url;
    }
}
