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

package com.pacific.adapter2;

import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.CompoundButton.OnCheckedChangeListener;

public interface ListenerProvider {
    /**
     * set OnClickListener
     *
     * @param listener
     */
    void setOnClickListener(OnClickListener listener);

    /**
     * get OnClickListener
     *
     * @return
     */
    OnClickListener getOnClickListener();

    /**
     * set OnTouchListener
     *
     * @param listener
     */
    void setOnTouchListener(OnTouchListener listener);

    /**
     * get OnTouchListeners
     *
     * @return
     */
    OnTouchListener getOnTouchListener();

    /**
     * set OnLongClickListener
     *
     * @param listener
     */
    void setOnLongClickListener(OnLongClickListener listener);

    /**
     * get OnLongClickListener
     *
     * @return
     */
    OnLongClickListener getOnLongClickListener();

    /**
     * set CompoundButton.OnCheckedChangeListener
     *
     * @param listener
     */
    void setOnCheckedChangeListener(OnCheckedChangeListener listener);

    /**
     * get CompoundButton.OnCheckedChangeListener
     *
     * @return
     */
    OnCheckedChangeListener getOnCheckedChangeListener();

    /**
     * set image loader to load image
     *
     * @param imageLoader
     */
    void setImageLoader(ImageLoader imageLoader);

    /**
     * get ImageLoader
     *
     * @return
     */
    ImageLoader getImageLoader();
}
