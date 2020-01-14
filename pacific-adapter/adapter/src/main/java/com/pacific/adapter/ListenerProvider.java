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

import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.CompoundButton.OnCheckedChangeListener;

import androidx.annotation.Nullable;

public interface ListenerProvider {
    /**
     * get OnClickListener
     *
     * @return
     */
    @Nullable
    OnClickListener getOnClickListener();

    /**
     * set OnClickListener
     *
     * @param listener
     */
    void setOnClickListener(@Nullable OnClickListener listener);

    /**
     * get OnTouchListeners
     *
     * @return
     */
    @Nullable
    OnTouchListener getOnTouchListener();

    /**
     * set OnTouchListener
     *
     * @param listener
     */
    void setOnTouchListener(@Nullable OnTouchListener listener);

    /**
     * get OnLongClickListener
     *
     * @return
     */
    @Nullable
    OnLongClickListener getOnLongClickListener();

    /**
     * set OnLongClickListener
     *
     * @param listener
     */
    void setOnLongClickListener(@Nullable OnLongClickListener listener);

    /**
     * get CompoundButton.OnCheckedChangeListener
     *
     * @return
     */
    @Nullable
    OnCheckedChangeListener getOnCheckedChangeListener();

    /**
     * set CompoundButton.OnCheckedChangeListener
     *
     * @param listener
     */
    void setOnCheckedChangeListener(@Nullable OnCheckedChangeListener listener);

    /**
     * get ImageLoader
     *
     * @return
     */
    @Nullable
    ImageLoader getImageLoader();

    /**
     * set image loader to load image
     *
     * @param imageLoader
     */
    void setImageLoader(@Nullable ImageLoader imageLoader);

    /**
     * get AdapterTextWatcher
     *
     * @return
     */
    @Nullable
    AdapterTextWatcher getTextChangedListener();

    /**
     * set AdapterTextWatcher
     *
     * @param textWatcher
     */
    void setTextChangedListener(@Nullable AdapterTextWatcher textWatcher);
}
