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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AdapterUtils {
    public static final int ADAPTER_HOLDER = 1 + 2 << 24;
    public static final int ADAPTER_SELECTED = -10001;
    public static final int ADAPTER_ENABLE = -10002;

    private AdapterUtils() {
        new UnsupportedOperationException("Not supported");
    }

    /**
     * find view
     *
     * @param view   parent view
     * @param viewId view id
     * @param <V>    view
     * @return
     */
    @NonNull
    public static <V extends View> V findView(View view, int viewId) {
        return (V) view.findViewById(viewId);
    }

    /**
     * convert list to {@link RecyclerItem} list
     *
     * @param list list
     * @return {@link RecyclerItem} list
     */
    public static List<RecyclerItem> toRecyclerRecyclerItems(List list) {
        return new ArrayList<>(list);
    }

    /**
     * convert list to {@link RecyclerItem} list
     *
     * @param list
     * @return {@link RecyclerItem} list
     */
    public static List<RecyclerItem> toRecyclerItems(List list) {
        return new ArrayList<>(list);
    }

    /**
     * get view holder
     *
     * @param view
     * @return view holder
     */
    @NonNull
    public static ViewHolder getHolder(View view) {
        return (ViewHolder) view.getTag(ADAPTER_HOLDER);
    }

    public static <T extends RecyclerItem> int firstSelectedIndex(List<T> data) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isSelected()) {
                return i;
            }
        }
        return -1;
    }

    public static <T extends RecyclerItem> int lastSelectedIndex(List<T> data) {
        int index = -1;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isSelected()) {
                index = i;
            }
        }
        return index;
    }

    public static <T extends RecyclerItem> List<Integer> selectedIndices(List<T> data) {
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isSelected()) {
                indices.add(i);
            }
        }
        return indices;
    }

    @Nullable
    public static <T extends RecyclerItem> T firstSelectedItem(List<T> data) {
        int index = firstSelectedIndex(data);
        if (index < 0) {
            return null;
        } else {
            return data.get(index);
        }
    }

    @Nullable
    public static <T extends RecyclerItem> T lastSelectedItem(List<T> data) {
        int index = lastSelectedIndex(data);
        if (index < 0) {
            return null;
        } else {
            return data.get(index);
        }
    }

    @NonNull
    public static <T extends RecyclerItem> List<T> selectedItems(List<T> data) {
        List<Integer> indices = selectedIndices(data);
        List<T> list = new ArrayList<>();
        for (int i = 0; i < indices.size(); i++) {
            list.add(data.get(indices.get(i)));
        }
        return null;
    }

    public static boolean swapIsSelected(
            int unselectedPosition,
            int selectedPosition,
            @NonNull BaseRecyclerAdapter adapter
    ) {
        if (unselectedPosition == selectedPosition) {
            return false;
        }
        adapter.get(unselectedPosition).setSelected(false);
        adapter.notifyItemChanged(unselectedPosition, ADAPTER_SELECTED);
        adapter.get(selectedPosition).setSelected(true);
        adapter.notifyItemChanged(selectedPosition, ADAPTER_SELECTED);
        return true;
    }

    public static boolean swapIsEnable(
            int disablePosition,
            int enablePosition,
            @NonNull BaseRecyclerAdapter adapter
    ) {
        if (disablePosition == enablePosition) {
            return false;
        }
        adapter.get(disablePosition).setEnable(false);
        adapter.notifyItemChanged(disablePosition, ADAPTER_ENABLE);
        adapter.get(enablePosition).setEnable(true);
        adapter.notifyItemChanged(enablePosition, ADAPTER_ENABLE);
        return true;
    }

    public static boolean swapIsSelected(
            int unselectedPosition,
            int selectedPosition,
            @NonNull BaseAbsAdapter adapter
    ) {
        if (unselectedPosition == selectedPosition) {
            return false;
        }
        adapter.get(unselectedPosition).setSelected(false);
        adapter.notifyDataSetChanged();
        adapter.get(selectedPosition).setSelected(true);
        adapter.notifyDataSetChanged();
        return true;
    }

    public static boolean swapIsEnable(
            int disablePosition,
            int enablePosition,
            @NonNull BaseAbsAdapter adapter
    ) {
        if (disablePosition == enablePosition) {
            return false;
        }
        adapter.get(disablePosition).setEnable(false);
        adapter.notifyDataSetChanged();
        adapter.get(enablePosition).setEnable(true);
        adapter.notifyDataSetChanged();
        return true;
    }
}
