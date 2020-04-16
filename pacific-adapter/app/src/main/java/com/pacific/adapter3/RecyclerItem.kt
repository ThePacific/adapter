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
package com.pacific.adapter3

import androidx.annotation.LayoutRes

interface RecyclerItem<T : ViewHolder> {

    var isSelected: Boolean

    var isEnable: Boolean

    /**
     * @return item view layout resource id
     */
    @LayoutRes
    fun getLayout(): Int

    /**
     * bind data callback
     *
     * @param holder view holder
     */
    fun bind(holder: ViewHolder)

    /**
     * unbind data callback
     *
     * @param holder view holder
     */
    fun unbind(holder: ViewHolder)

    /**
     * bind pay loads
     *
     * @param holder   view holder
     * @param payloads data
     */
    fun bindPayloads(holder: ViewHolder, payloads: List<Any>?)

    /**
     * get span size , for GridLayoutManager
     *
     * @param spanCount span
     * @param position  adapter position
     * @return
     */
    fun getSpanSize(spanCount: Int, position: Int): Int

    /**
     * onViewAttachedToWindow callback
     *
     * @param holder
     */
    fun onViewAttachedToWindow(holder: T)

    /**
     * onViewDetachedFromWindow callback
     *
     * @param holder
     */
    fun onViewDetachedFromWindow(holder: T)

    /**
     * do some cleaning works
     */
    fun onDestroy()
}