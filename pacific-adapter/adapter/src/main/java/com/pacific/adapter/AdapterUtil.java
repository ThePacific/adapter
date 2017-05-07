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

import java.util.ArrayList;
import java.util.List;

public class AdapterUtil {
    private AdapterUtil() {
        new AssertionError("Not supported");
    }

    /**
     * find view
     *
     * @param view   parent view
     * @param viewId view id
     * @param <V>    view
     * @return
     */
    public static <V extends View> V findView(View view, int viewId) {
        return (V) view.findViewById(viewId);
    }

    /**
     * convert list to {@link RecyclerItem} list
     *
     * @param list list
     * @return {@link RecyclerItem} list
     */
    public static List<RecyclerItem> toRecyclerItems(List list) {
        return new ArrayList<>(list);
    }

    /**
     * convert list to {@link Item} list
     *
     * @param list
     * @return {@link Item} list
     */
    public static List<Item> toItems(List list) {
        return new ArrayList<>(list);
    }

    /**
     * get view holder
     *
     * @param view
     * @return view holder
     */
    public static ViewHolder getHolder(View view) {
        return (ViewHolder) view.getTag(R.integer.adapter_holder);
    }
}
