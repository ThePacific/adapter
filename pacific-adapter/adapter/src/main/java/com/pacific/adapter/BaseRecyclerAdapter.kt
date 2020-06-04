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

import android.view.LayoutInflater
import android.view.View
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T : RecyclerItem>(
    private val data: ArrayList<T>
) : RecyclerView.Adapter<AdapterViewHolder>(), DataIO<T>, ListenerProvider {

    private val provider: ListenerProviderImpl = ListenerProviderImpl()
    protected var inflater: LayoutInflater? = null
    var onDataSetChanged: OnDataSetChanged? = null

    private fun onDataSetChanged() {
        onDataSetChanged?.apply(data.size)
    }

    override fun getItemViewType(position: Int): Int = get<T>(position).getLayout()

    override fun onViewRecycled(holder: AdapterViewHolder) {
        val position = holder.bindingAdapterPosition
        if (position != RecyclerView.NO_POSITION) {
            get<T>(position).unbind(holder)
            return
        }
        super.onViewRecycled(holder)
    }

    override fun onViewAttachedToWindow(holder: AdapterViewHolder) {
        val position = holder.bindingAdapterPosition
        if (position != RecyclerView.NO_POSITION) {
            get<T>(position).onViewAttachedToWindow(holder)
        }
    }

    override fun onViewDetachedFromWindow(holder: AdapterViewHolder) {
        val position = holder.bindingAdapterPosition
        if (position != RecyclerView.NO_POSITION) {
            get<T>(position).onViewDetachedFromWindow(holder)
        }
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val item = get<T>(position)
        holder.size = itemCount
        holder.item = item
        item.bind(holder)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int, payloads: List<Any>) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val item = get<T>(position)
            holder.size = itemCount
            holder.item = item
            item.bindPayloads(holder, payloads)
        }
    }

    override fun clear() {
        if (data.isNotEmpty()) {
            data.clear()
            notifyDataSetChanged()
            onDataSetChanged()
        }
    }

    override fun add(element: T) {
        val size = data.size
        if (data.add(element)) {
            notifyItemInserted(size)
            onDataSetChanged()
        }
    }

    override fun remove(element: T) {
        val index = data.indexOf(element)
        if (data.remove(element)) {
            notifyItemRemoved(index)
            onDataSetChanged()
        }
    }

    override fun add(index: Int, element: T) {
        val size = itemCount
        require(index < size)
        data.add(index, element)
        if (data.size > size) {
            notifyItemInserted(index)
            onDataSetChanged()
        }
    }

    override fun addAll(list: List<T>) {
        val size = data.size
        if (data.addAll(list)) {
            notifyItemRangeInserted(size, list.size)
            onDataSetChanged()
        }
    }

    override fun addAll(index: Int, list: List<T>) {
        if (data.addAll(index, list)) {
            notifyItemRangeInserted(index, list.size)
            onDataSetChanged()
        }
    }

    override fun removeAll(list: List<T>) {
        val indexes: MutableList<Int> = ArrayList()
        var index: Int
        for (item in list) {
            index = data.indexOf(item)
            if (index >= 0) {
                indexes.add(index)
            }
        }
        if (data.removeAll(list)) {
            for (k in indexes.indices) {
                notifyItemRemoved(indexes[k])
            }
            onDataSetChanged()
        }
    }

    override fun retainAll(list: List<T>) {
        val indexes: MutableList<Int> = ArrayList()
        var index: Int
        for (item in list) {
            index = data.indexOf(item)
            if (index >= 0) {
                indexes.add(index)
            }
        }
        val size = data.size
        if (data.retainAll(list)) {
            for (i in 0 until size) {
                if (!indexes.contains(i)) {
                    notifyItemRemoved(i)
                }
            }
            onDataSetChanged()
        }
    }

    override fun getAll(): List<T> {
        return data
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T2 : T> get(index: Int): T2 {
        require(index < data.size)
        return data[index] as T2
    }

    override fun replaceAt(index: Int, element: T) {
        data[index] = element
        notifyItemChanged(index)
    }

    override fun replace(oldElement: T, newElement: T) {
        replaceAt(data.indexOf(oldElement), newElement)
    }

    override fun replaceAll(list: List<T>) {
        if (data.isNotEmpty()) {
            data.clear()
        }
        data.addAll(list)
        notifyDataSetChanged()
        onDataSetChanged()
    }

    override fun replaceAll(index: Int, list: List<T>) {
        replaceAll(index, list, false)
    }

    fun replaceAll(index: Int, list: List<T>, notifyDataSetChanged: Boolean) {
        val size = data.size
        if (index >= size) {
            addAll(list)
        } else {
            for (i in index until size) {
                data.removeAt(index)
            }
            if (!notifyDataSetChanged) {
                notifyItemRangeRemoved(index, size - index)
            }
            if (data.addAll(list) && !notifyDataSetChanged) {
                notifyItemRangeInserted(index, list.size)
            }
            if (notifyDataSetChanged) {
                notifyDataSetChanged()
            }
            onDataSetChanged()
        }
    }

    override fun remove(index: Int): T {
        val obj = data.removeAt(index)
        notifyItemRemoved(index)
        onDataSetChanged()
        return obj
    }

    override fun subList(fromIndex: Int, toIndex: Int): List<T> {
        return if (fromIndex < 0 || toIndex >= itemCount) {
            emptyList()
        } else {
            data.subList(fromIndex, toIndex)
        }
    }

    override fun firstSelectedIndex(): Int = firstSelectedIndex(data)

    override fun lastSelectedIndex(): Int = lastSelectedIndex(data)

    override fun selectedIndices(): List<Int> = selectedIndices(data)

    override fun firstSelectedItem(): T? = firstSelectedItem(data)

    override fun lastSelectedItem(): T? = lastSelectedItem(data)

    override fun selectedItems(): List<T> = selectedItems(data)

    override fun getItemCount(): Int = data.size

    override fun isEmpty(): Boolean = data.isEmpty()

    override fun contains(element: T): Boolean = data.contains(element)

    override fun containsAll(list: List<T>): Boolean = data.containsAll(list)

    override fun indexOf(element: T): Int = data.indexOf(element)

    override fun lastIndexOf(element: T): Int = data.lastIndexOf(element)

    override var onClickListener: View.OnClickListener?
        get() = provider.onClickListener
        set(value) {
            provider.onClickListener = value
        }

    override var onTouchListener: View.OnTouchListener?
        get() = provider.onTouchListener
        set(value) {
            provider.onTouchListener = value
        }

    override var onLongClickListener: View.OnLongClickListener?
        get() = provider.onLongClickListener
        set(value) {
            provider.onLongClickListener = value
        }

    override var onCheckedChangeListener: CompoundButton.OnCheckedChangeListener?
        get() = provider.onCheckedChangeListener
        set(value) {
            provider.onCheckedChangeListener = value
        }

    override var imageLoader: AdapterImageLoader?
        get() = provider.imageLoader
        set(value) {
            provider.imageLoader = value
        }

    override var textChangedListener: AdapterTextWatcher?
        get() = provider.textChangedListener
        set(value) {
            provider.textChangedListener = value
        }
}