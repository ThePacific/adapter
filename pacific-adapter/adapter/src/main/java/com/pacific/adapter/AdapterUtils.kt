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

import android.view.View
import java.util.*

object AdapterUtils {

    const val ADAPTER_HOLDER = 1 + 2 shl 24
    const val ADAPTER_SELECTED = -10001
    const val ADAPTER_ENABLE = -10002

    @Suppress("UNCHECKED_CAST")
    fun getHolder(view: View): AdapterViewHolder = view.getTag(ADAPTER_HOLDER) as AdapterViewHolder

    fun <T : RecyclerItem> firstSelectedIndex(data: List<T>): Int {
        for (i in data.indices) {
            if (data[i].isSelected) {
                return i
            }
        }
        return -1
    }

    fun <T : RecyclerItem> lastSelectedIndex(data: List<T>): Int {
        var index = -1
        for (i in data.indices) {
            if (data[i].isSelected) {
                index = i
            }
        }
        return index
    }

    fun <T : RecyclerItem> selectedIndices(data: List<T>): List<Int> {
        val indices: MutableList<Int> = ArrayList()
        for (i in data.indices) {
            if (data[i].isSelected) {
                indices.add(i)
            }
        }
        return indices
    }

    fun <T : RecyclerItem> firstSelectedItem(data: List<T>): T? {
        val index = firstSelectedIndex(data)
        return if (index < 0) {
            null
        } else {
            data[index]
        }
    }

    fun <T : RecyclerItem> lastSelectedItem(data: List<T>): T? {
        val index = lastSelectedIndex(data)
        return if (index < 0) {
            null
        } else {
            data[index]
        }
    }

    fun <T : RecyclerItem> selectedItems(data: List<T>): List<T> {
        val indices = selectedIndices(data)
        val list: MutableList<T> = ArrayList()
        for (i in indices.indices) {
            list.add(data[indices[i]])
        }
        return list
    }

    fun swapIsSelected(
        unselectedPosition: Int,
        selectedPosition: Int,
        adapter: BaseRecyclerAdapter<RecyclerItem>
    ): Boolean {
        if (adapter.isEmpty()) {
            return true
        }
        if (unselectedPosition == -1 && selectedPosition == -1) {
            return false
        }
        require(selectedPosition >= 0 && selectedPosition < adapter.itemCount)
        if (unselectedPosition == selectedPosition) {
            val item = adapter.get<RecyclerItem>(unselectedPosition)
            item.isSelected = !item.isSelected
            adapter.notifyItemChanged(unselectedPosition, ADAPTER_SELECTED)
        } else {
            if (unselectedPosition >= 0) {
                adapter.get<RecyclerItem>(unselectedPosition).isSelected = false
                adapter.notifyItemChanged(unselectedPosition, ADAPTER_SELECTED)
            }
            adapter.get<RecyclerItem>(selectedPosition).isSelected = true
            adapter.notifyItemChanged(selectedPosition, ADAPTER_SELECTED)
        }
        return true
    }

    fun swapIsEnable(
        disablePosition: Int,
        enablePosition: Int,
        adapter: BaseRecyclerAdapter<RecyclerItem>
    ): Boolean {
        if (adapter.isEmpty()) {
            return true
        }
        if (disablePosition == -1 && enablePosition == -1) {
            return false
        }
        require(enablePosition >= 0 && enablePosition < adapter.itemCount)
        if (disablePosition == enablePosition) {
            val item = adapter.get<RecyclerItem>(disablePosition)
            item.isEnable = !item.isEnable
            adapter.notifyItemChanged(disablePosition, ADAPTER_ENABLE)
        } else {
            if (disablePosition >= 0) {
                adapter.get<RecyclerItem>(disablePosition).isEnable = false
                adapter.notifyItemChanged(disablePosition, ADAPTER_ENABLE)
            }
            adapter.get<RecyclerItem>(enablePosition).isEnable = true
            adapter.notifyItemChanged(enablePosition, ADAPTER_ENABLE)
        }
        return true
    }

    fun selectAll(adapter: BaseRecyclerAdapter<RecyclerItem>, isSelected: Boolean) {
        if (adapter.isEmpty()) {
            return
        }
        adapter.getAll().forEach {
            it.isSelected = isSelected
        }
        adapter.notifyItemRangeChanged(0, adapter.itemCount, ADAPTER_SELECTED)
    }

    fun enableAll(adapter: BaseRecyclerAdapter<RecyclerItem>, isEnable: Boolean) {
        if (adapter.isEmpty()) {
            return
        }
        adapter.getAll().forEach {
            it.isEnable = isEnable
        }
        adapter.notifyItemRangeChanged(0, adapter.itemCount, ADAPTER_ENABLE)
    }
}