package com.pacific.adapter;

import android.widget.TextView;

import androidx.annotation.NonNull;

public abstract class AfterAdapterTextWatcher implements AdapterTextWatcher {
    @Override
    public void beforeTextChanged(@NonNull TextView v, CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(@NonNull TextView v, CharSequence s, int start, int before, int count) {
    }
}
