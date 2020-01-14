/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pacific.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.List;

public final class RecyclerAdapter extends BaseRecyclerAdapter<RecyclerItem, ViewHolder> {

    public RecyclerAdapter() {
        super();
    }

    public RecyclerAdapter(List<RecyclerItem> data) {
        super(data);
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        ViewHolder holder = new ViewHolder(
                inflater.inflate(viewType, parent, false),
                this
        );
        if (onCreateViewHolder != null) {
            onCreateViewHolder.onCreateViewHolder(holder, viewType);
        }
        return holder;
    }
}
