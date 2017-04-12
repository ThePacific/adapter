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
import android.util.SparseArray;
import android.view.View;
import android.widget.CompoundButton;

public final class ListenerProviderImpl implements ListenerProvider {
    /**
     * OnClickListener array
     */
    private SparseArray<View.OnClickListener> onClickListeners;

    /**
     * OnTouchListener array
     */
    private SparseArray<View.OnTouchListener> onTouchListeners;

    /**
     * OnLongClickListener array
     */
    private SparseArray<View.OnLongClickListener> onLongClickListeners;

    /**
     * CompoundButton.OnCheckedChangeListener array
     */
    private SparseArray<CompoundButton.OnCheckedChangeListener> onCheckedChangeListeners;

    private ImageLoader imageLoader;

    /**
     * clear all listener
     */
    @Override
    public void clearListeners() {
        if (onClickListeners != null) {
            onClickListeners.clear();
        }
        if (onTouchListeners != null) {
            onTouchListeners.clear();
        }
        if (onLongClickListeners != null) {
            onLongClickListeners.clear();
        }
        if (onCheckedChangeListeners != null) {
            onCheckedChangeListeners.clear();
        }
    }

    /**
     * add OnClickListener
     *
     * @param layout   item layout resource id
     * @param listener
     */
    @Override
    public void addOnClickListener(@LayoutRes int layout, View.OnClickListener listener) {
        if (onClickListeners == null) {
            onClickListeners = new SparseArray<>();
        }
        onClickListeners.put(layout, listener);
    }

    /**
     * get OnClickListener
     *
     * @param layout item layout resource id
     * @return
     */
    @Override
    public View.OnClickListener getOnClickListener(@LayoutRes int layout) {
        if (onClickListeners != null) {
            return onClickListeners.get(layout);
        }
        return null;
    }

    /**
     * add OnTouchListener
     *
     * @param layout   item layout resource id
     * @param listener
     */
    @Override
    public void addOnTouchListener(@LayoutRes int layout, View.OnTouchListener listener) {
        if (onTouchListeners == null) {
            onTouchListeners = new SparseArray<>();
        }
        onTouchListeners.put(layout, listener);
    }

    /**
     * get OnTouchListeners
     *
     * @param layout item layout resource id
     * @return
     */
    @Override
    public View.OnTouchListener getOnTouchListener(@LayoutRes int layout) {
        if (onTouchListeners != null) {
            return onTouchListeners.get(layout);
        }
        return null;
    }

    /**
     * add OnLongClickListener
     *
     * @param layout   item layout resource id
     * @param listener
     */
    @Override
    public void addOnLongClickListener(@LayoutRes int layout, View.OnLongClickListener listener) {
        if (onLongClickListeners == null) {
            onLongClickListeners = new SparseArray<>();
        }
        onLongClickListeners.put(layout, listener);
    }

    /**
     * get OnLongClickListener
     *
     * @param layout item layout resource id
     * @return
     */
    @Override
    public View.OnLongClickListener getOnLongClickListener(@LayoutRes int layout) {
        if (onLongClickListeners != null) {
            return onLongClickListeners.get(layout);
        }
        return null;
    }

    /**
     * add CompoundButton.OnCheckedChangeListener
     *
     * @param layout   item layout resource id
     * @param listener
     */
    @Override
    public void addOnCheckedChangeListener(@LayoutRes int layout,
                                           CompoundButton.OnCheckedChangeListener listener) {
        if (onCheckedChangeListeners == null) {
            onCheckedChangeListeners = new SparseArray<>();
        }
        onCheckedChangeListeners.put(layout, listener);
    }

    /**
     * get CompoundButton.OnCheckedChangeListener
     *
     * @param layout item layout resource id
     * @return
     */
    @Override
    public CompoundButton.OnCheckedChangeListener getOnCheckedChangeListener(@LayoutRes int layout) {
        if (onCheckedChangeListeners != null) {
            return onCheckedChangeListeners.get(layout);
        }
        return null;
    }

    /**
     * add image loader to load image
     *
     * @param imageLoader
     */
    @Override
    public void addImageLoader(ImageLoader imageLoader) {
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
