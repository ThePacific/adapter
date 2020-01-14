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

import androidx.annotation.NonNull;

import java.util.List;

public abstract class SimpleRecyclerItem implements RecyclerItem<ViewHolder> {

    protected boolean isSelected = false;

    protected boolean isEnable = false;

    @Override
    public void bindPayloads(@NonNull ViewHolder holder, @NonNull List<Object> payloads) {
    }

    @Override
    public int getSpanSize(int spanCount, int position) {
        return spanCount;
    }

    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder holder) {
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull ViewHolder holder) {
    }

    @Override
    public void unbind(@NonNull ViewHolder holder) {
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public boolean isEnable() {
        return isEnable;
    }

    @Override
    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }

    @Override
    public void setEnable(boolean isEnable) {
        this.isEnable = isEnable;
    }

    @Override
    public void onDestroy() {
    }
}
