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

import android.support.annotation.LayoutRes;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.CompoundButton.OnCheckedChangeListener;

public interface ListenerProvider {

    /**
     * clear all listener
     */
    void clearListeners();

    /**
     * add OnClickListener
     *
     * @param layout   item layout resource id
     * @param listener
     */
    void addOnClickListener(@LayoutRes int layout, OnClickListener listener);

    /**
     * get OnClickListener
     *
     * @param layout item layout resource id
     * @return
     */
    OnClickListener getOnClickListener(@LayoutRes int layout);

    /**
     * add OnTouchListener
     *
     * @param layout   item layout resource id
     * @param listener
     */
    void addOnTouchListener(@LayoutRes int layout, OnTouchListener listener);

    /**
     * get OnTouchListeners
     *
     * @param layout item layout resource id
     * @return
     */
    OnTouchListener getOnTouchListener(@LayoutRes int layout);

    /**
     * add OnLongClickListener
     *
     * @param layout   item layout resource id
     * @param listener
     */
    void addOnLongClickListener(@LayoutRes int layout, OnLongClickListener listener);

    /**
     * get OnLongClickListener
     *
     * @param layout item layout resource id
     * @return
     */
    OnLongClickListener getOnLongClickListener(@LayoutRes int layout);

    /**
     * add CompoundButton.OnCheckedChangeListener
     *
     * @param layout   item layout resource id
     * @param listener
     */
    void addOnCheckedChangeListener(@LayoutRes int layout, OnCheckedChangeListener listener);

    /**
     * get CompoundButton.OnCheckedChangeListener
     *
     * @param layout item layout resource id
     * @return
     */
    OnCheckedChangeListener getOnCheckedChangeListener(@LayoutRes int layout);
}
