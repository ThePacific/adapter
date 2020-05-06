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

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.IdRes

internal class ListenerAttachImpl(
    private val holder: AdapterViewHolder,
    private val provider: ListenerProvider
) : ListenerAttach {

    private var textWatcher: TextWatcher? = null

    override fun attachOnClickListener(@IdRes viewId: Int) {
        val view = holder.itemView.findViewById<View>(viewId)
        view.setTag(ADAPTER_HOLDER, holder)
        view.setOnClickListener(provider.onClickListener)
    }

    override fun attachOnTouchListener(@IdRes viewId: Int) {
        val view = holder.itemView.findViewById<View>(viewId)
        view.setTag(ADAPTER_HOLDER, holder)
        view.setOnTouchListener(provider.onTouchListener)
    }

    override fun attachOnLongClickListener(@IdRes viewId: Int) {
        val view = holder.itemView.findViewById<View>(viewId)
        view.setTag(ADAPTER_HOLDER, holder)
        view.setOnLongClickListener(provider.onLongClickListener)
    }

    override fun attachOnCheckedChangeListener(@IdRes viewId: Int) {
        val view = holder.itemView.findViewById<CompoundButton>(viewId)
        view.setTag(ADAPTER_HOLDER, holder)
        view.setOnCheckedChangeListener(provider.onCheckedChangeListener)
    }

    override fun attachImageLoader(@IdRes viewId: Int) {
        provider.imageLoader?.let {
            val view = holder.itemView.findViewById<ImageView>(viewId)
            view.setTag(ADAPTER_HOLDER, holder)
            it.load(view, holder)
        }
    }

    override fun attachTextChangedListener(@IdRes viewId: Int) {
        val view = holder.itemView.findViewById<TextView>(viewId)
        if (textWatcher != null) {
            view.removeTextChangedListener(textWatcher)
        }

        val adapterTextWatcher = provider.textChangedListener ?: return
        if (textWatcher == null) {
            textWatcher = object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence, start: Int, count: Int, after: Int
                ) {
                    adapterTextWatcher.beforeTextChanged(view, s, start, count, after, holder)
                }

                override fun onTextChanged(
                    s: CharSequence, start: Int, before: Int, count: Int
                ) {
                    adapterTextWatcher.onTextChanged(view, s, start, before, count, holder)
                }

                override fun afterTextChanged(s: Editable) {
                    adapterTextWatcher.afterTextChanged(view, s, holder)
                }
            }
        }
        view.setTag(ADAPTER_HOLDER, holder)
        view.addTextChangedListener(textWatcher)
    }

    override fun detachTextChangedListener(@IdRes viewId: Int) {
        if (textWatcher != null) {
            holder.itemView.findViewById<TextView>(viewId).removeTextChangedListener(textWatcher)
        }
    }
}