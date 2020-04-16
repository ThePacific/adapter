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

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView

open class ViewHolder(
    itemView: View,
    private val adapter: BaseRecyclerAdapter<*, *>
) : RecyclerView.ViewHolder(itemView), ListenerAttach {

    private val listenerAttach: ListenerAttachImpl = ListenerAttachImpl(this, adapter)

    private var binding: Any? = null

    internal lateinit var item: RecyclerItem<*>

    var size: Int = -1
        internal set

    var itemPosition: Int = -1
        internal set

    val isFirstItem: Boolean
        get() = itemPosition == 0

    val isLastItem: Boolean
        get() = itemPosition == size - 1

    override fun attachOnClickListener(@IdRes viewId: Int) {
        listenerAttach.attachOnClickListener(viewId)
    }

    override fun attachOnTouchListener(@IdRes viewId: Int) {
        listenerAttach.attachOnTouchListener(viewId)
    }

    override fun attachOnLongClickListener(@IdRes viewId: Int) {
        listenerAttach.attachOnLongClickListener(viewId)
    }

    override fun attachOnCheckedChangeListener(@IdRes viewId: Int) {
        listenerAttach.attachOnCheckedChangeListener(viewId)
    }

    override fun attachImageLoader(@IdRes viewId: Int) {
        listenerAttach.attachImageLoader(viewId)
    }

    override fun attachTextChangedListener(@IdRes viewId: Int) {
        listenerAttach.attachTextChangedListener(viewId)
    }

    override fun detachTextChangedListener(@IdRes viewId: Int) {
        listenerAttach.detachTextChangedListener(viewId)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : RecyclerItem<*>> item(): T = item as T

    @Suppress("UNCHECKED_CAST")
    fun <T : BaseRecyclerAdapter<*, *>> adapter(): T = adapter as T

    @Suppress("UNCHECKED_CAST")
    fun <T : ViewGroup> parentViewGroup(): T = itemView.parent as T

    @Suppress("UNCHECKED_CAST")
    fun <T : Activity> activity(): T = itemView.context as T

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> binding(viewBinding: ((View) -> T)? = null): T {
        if (binding == null && viewBinding != null) {
            binding = viewBinding.invoke(itemView)
        }
        return binding as T
    }
}