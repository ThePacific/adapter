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
package com.pacific.adapter

import androidx.annotation.IdRes

internal interface ListenerAttach {
    /**
     * attach OnClickListener for view
     *
     * @param viewId view id
     */
    fun attachOnClickListener(@IdRes viewId: Int)

    /**
     * attach OnTouchListener for view
     *
     * @param viewId view id
     */
    fun attachOnTouchListener(@IdRes viewId: Int)

    /**
     * attach OnLongClickListener for view
     *
     * @param viewId view id
     */
    fun attachOnLongClickListener(@IdRes viewId: Int)

    /**
     * attach CompoundButton.OnCheckedChangeListener for CompoundButton
     *
     * @param viewId CompoundButton view id
     */
    fun attachOnCheckedChangeListener(@IdRes viewId: Int)

    /**
     * load image
     *
     * @param viewId
     */
    fun attachImageLoader(@IdRes viewId: Int)

    /**
     * attach TextChangedListener for TextView or EditText
     *
     * @param viewId
     */
    fun attachTextChangedListener(@IdRes viewId: Int)

    /**
     * detach TextChangedListener for TextView or EditText
     *
     * @param viewId
     */
    fun detachTextChangedListener(@IdRes viewId: Int)
}