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

public final class ListenerProviderImpl implements ListenerProvider {
    /**
     * OnClickListener
     */
    private View.OnClickListener onClickListener;

    /**
     * OnTouchListener
     */
    private View.OnTouchListener onTouchListener;

    /**
     * OnLongClickListener
     */
    private View.OnLongClickListener onLongClickListener;

    /**
     * CompoundButton.OnCheckedChangeListener
     */
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener;

    private ImageLoader imageLoader;

    /**
     * set OnClickListener
     *
     * @param listener
     */
    @Override
    public void setOnClickListener(View.OnClickListener listener) {
        this.onClickListener = listener;
    }

    /**
     * get OnClickListener
     *
     * @return
     */
    @Override
    public View.OnClickListener getOnClickListener() {
        return this.onClickListener;
    }

    /**
     * set OnTouchListener
     *
     * @param listener
     */
    @Override
    public void setOnTouchListener(View.OnTouchListener listener) {
        this.onTouchListener = listener;
    }

    /**
     * get OnTouchListeners
     *
     * @return
     */
    @Override
    public View.OnTouchListener getOnTouchListener() {
        return this.onTouchListener;
    }

    /**
     * set OnLongClickListener
     *
     * @param listener
     */
    @Override
    public void setOnLongClickListener(View.OnLongClickListener listener) {
        this.onLongClickListener = listener;
    }

    /**
     * get OnLongClickListener
     *
     * @return
     */
    @Override
    public View.OnLongClickListener getOnLongClickListener() {
        return this.onLongClickListener;
    }

    /**
     * set CompoundButton.OnCheckedChangeListener
     *
     * @param listener
     */
    @Override
    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener listener) {
        this.onCheckedChangeListener = listener;
    }

    /**
     * get CompoundButton.OnCheckedChangeListener
     *
     * @return
     */
    @Override
    public CompoundButton.OnCheckedChangeListener getOnCheckedChangeListener() {
        return this.onCheckedChangeListener;
    }

    /**
     * set image loader to load image
     *
     * @param imageLoader
     */
    @Override
    public void setImageLoader(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    /**
     * get ImageLoader
     *
     * @return
     */
    @Override
    public ImageLoader getImageLoader() {
        return this.imageLoader;
    }
}
