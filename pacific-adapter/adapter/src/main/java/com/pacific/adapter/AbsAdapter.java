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

import android.view.View;

import java.util.List;

/**
 * AbsAdapter for AdapterView , like ListView,GridView and Spinner
 */
public final class AbsAdapter extends BaseAbsAdapter<Item, ViewHolder> {
    public AbsAdapter() {
        super();
    }

    public AbsAdapter(int viewTypeCount) {
        super(viewTypeCount);
    }

    public AbsAdapter(List<Item> data, int viewTypeCount) {
        super(data, viewTypeCount);
    }

    /**
     * create ViewHolder
     *
     * @param convertView item view
     * @return ViewHolder
     */
    @Override
    protected ViewHolder createViewHolder(View convertView) {
        return new ViewHolder(convertView, this);
    }
}
