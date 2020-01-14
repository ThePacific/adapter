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
import android.widget.CompoundButton;

import androidx.annotation.Nullable;

public final class ListenerProviderImpl implements ListenerProvider {
    /**
     * OnClickListener
     */
    @Nullable
    private View.OnClickListener onClickListener;

    /**
     * OnTouchListener
     */
    @Nullable
    private View.OnTouchListener onTouchListener;

    /**
     * OnLongClickListener
     */
    @Nullable
    private View.OnLongClickListener onLongClickListener;

    /**
     * CompoundButton.OnCheckedChangeListener
     */
    @Nullable
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener;

    @Nullable
    private ImageLoader imageLoader;

    @Nullable
    private AdapterTextWatcher adapterTextWatcher;

    /**
     * get OnClickListener
     *
     * @return
     */
    @Override
    @Nullable
    public View.OnClickListener getOnClickListener() {
        return this.onClickListener;
    }

    /**
     * set OnClickListener
     *
     * @param listener
     */
    @Override
    public void setOnClickListener(@Nullable View.OnClickListener listener) {
        this.onClickListener = listener;
    }

    /**
     * get OnTouchListeners
     *
     * @return
     */
    @Override
    @Nullable
    public View.OnTouchListener getOnTouchListener() {
        return this.onTouchListener;
    }

    /**
     * set OnTouchListener
     *
     * @param listener
     */
    @Override
    public void setOnTouchListener(@Nullable View.OnTouchListener listener) {
        this.onTouchListener = listener;
    }

    /**
     * get OnLongClickListener
     *
     * @return
     */
    @Override
    @Nullable
    public View.OnLongClickListener getOnLongClickListener() {
        return this.onLongClickListener;
    }

    /**
     * set OnLongClickListener
     *
     * @param listener
     */
    @Override
    public void setOnLongClickListener(@Nullable View.OnLongClickListener listener) {
        this.onLongClickListener = listener;
    }

    /**
     * get CompoundButton.OnCheckedChangeListener
     *
     * @return
     */
    @Override
    @Nullable
    public CompoundButton.OnCheckedChangeListener getOnCheckedChangeListener() {
        return this.onCheckedChangeListener;
    }

    /**
     * set CompoundButton.OnCheckedChangeListener
     *
     * @param listener
     */
    @Override
    public void setOnCheckedChangeListener(@Nullable CompoundButton.OnCheckedChangeListener listener) {
        this.onCheckedChangeListener = listener;
    }

    /**
     * get ImageLoader
     *
     * @return
     */
    @Override
    @Nullable
    public ImageLoader getImageLoader() {
        return this.imageLoader;
    }

    /**
     * set image loader to load image
     *
     * @param imageLoader
     */
    @Override
    public void setImageLoader(@Nullable ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    @Override
    @Nullable
    public AdapterTextWatcher getTextChangedListener() {
        return this.adapterTextWatcher;
    }

    @Override
    public void setTextChangedListener(@Nullable AdapterTextWatcher textWatcher) {
        this.adapterTextWatcher = textWatcher;
    }
}
