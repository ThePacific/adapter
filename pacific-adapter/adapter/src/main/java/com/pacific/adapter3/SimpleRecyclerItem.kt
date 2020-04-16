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

abstract class SimpleRecyclerItem : RecyclerItem<ViewHolder> {

    override var isSelected = false

    override var isEnable = false

    override fun bindPayloads(holder: ViewHolder, payloads: List<Any>?) {}

    override fun getSpanSize(spanCount: Int, position: Int): Int = spanCount

    override fun onViewAttachedToWindow(holder: ViewHolder) {}

    override fun onViewDetachedFromWindow(holder: ViewHolder) {}

    override fun unbind(holder: ViewHolder) {}

    override fun onDestroy() {}
}