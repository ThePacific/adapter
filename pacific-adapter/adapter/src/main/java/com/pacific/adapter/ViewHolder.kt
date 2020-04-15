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

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(
    itemView: View,
    provider: ListenerProvider
) : RecyclerView.ViewHolder(itemView), ListenerAttach {

    private val listenerAttach: ListenerAttachImpl = ListenerAttachImpl(this, provider)
    private val binding: Any by lazy {
        try {
            val clazz: Class<*> = try {
                Class.forName("androidx.databinding.DataBindingUtil")
            } catch (ignored: Exception) {
                Class.forName("android.databinding.DataBindingUtil")
            }
            clazz.getMethod("bind", View::class.java).invoke(null, itemView)
        } catch (ignored: Exception) {
            DefaultBinding(itemView)
        }
    }

    var size: Int = -1
        internal set

    var itemPosition: Int = -1
        internal set

    lateinit var item: RecyclerItem<*>
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
    fun <T : Any> binding(): T = binding as T

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> adapter(): T = listenerAttach.provider as T

    @Suppress("UNCHECKED_CAST")
    fun <T : ViewGroup> parentViewGroup(): T = itemView.parent as T

    @Suppress("UNCHECKED_CAST")
    fun <T : Activity> activity(): T = itemView.context as T
}