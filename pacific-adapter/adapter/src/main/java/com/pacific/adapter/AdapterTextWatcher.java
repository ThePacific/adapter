package com.pacific.adapter;

import android.text.Editable;
import android.widget.TextView;

import androidx.annotation.NonNull;

public interface AdapterTextWatcher {

    void beforeTextChanged(@NonNull TextView v, CharSequence s, int start, int count, int after);

    void onTextChanged(@NonNull TextView v, CharSequence s, int start, int before, int count);

    void afterTextChanged(@NonNull TextView v, @NonNull Editable s);
}
