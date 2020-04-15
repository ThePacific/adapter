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

const val ADAPTER_HOLDER = 1 + 2 shl 24
const val ADAPTER_SELECTED = -10001
const val ADAPTER_ENABLE = -10002

@Suppress("UNCHECKED_CAST")
fun getHolder(view: View): ViewHolder = view.getTag(ADAPTER_HOLDER) as ViewHolder

fun <T : RecyclerItem<*>> firstSelectedIndex(data: List<T>): Int {
    for (i in data.indices) {
        if (data[i].isSelected) {
            return i
        }
    }
    return -1
}

fun <T : RecyclerItem<*>> lastSelectedIndex(data: List<T>): Int {
    var index = -1
    for (i in data.indices) {
        if (data[i].isSelected) {
            index = i
        }
    }
    return index
}

fun <T : RecyclerItem<*>> selectedIndices(data: List<T>): List<Int> {
    val indices: MutableList<Int> = ArrayList()
    for (i in data.indices) {
        if (data[i].isSelected) {
            indices.add(i)
        }
    }
    return indices
}

fun <T : RecyclerItem<*>> firstSelectedItem(data: List<T>): T? {
    val index = firstSelectedIndex(data)
    return if (index < 0) {
        null
    } else {
        data[index]
    }
}

fun <T : RecyclerItem<*>> lastSelectedItem(data: List<T>): T? {
    val index = lastSelectedIndex(data)
    return if (index < 0) {
        null
    } else {
        data[index]
    }
}

fun <T : RecyclerItem<*>> selectedItems(data: List<T>): List<T> {
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
    adapter: BaseRecyclerAdapter<*, RecyclerItem<*>>
): Boolean {
    if (unselectedPosition == selectedPosition) {
        return false
    }
    adapter.get<RecyclerItem<ViewHolder>>(unselectedPosition).isSelected = false
    adapter.notifyItemChanged(unselectedPosition, ADAPTER_SELECTED)
    adapter.get<RecyclerItem<ViewHolder>>(selectedPosition).isSelected = true
    adapter.notifyItemChanged(selectedPosition, ADAPTER_SELECTED)
    return true
}

fun swapIsEnable(
    disablePosition: Int,
    enablePosition: Int,
    adapter: BaseRecyclerAdapter<*, RecyclerItem<*>>
): Boolean {
    if (disablePosition == enablePosition) {
        return false
    }
    adapter.get<RecyclerItem<ViewHolder>>(disablePosition).isEnable = false
    adapter.notifyItemChanged(disablePosition, ADAPTER_ENABLE)
    adapter.get<RecyclerItem<ViewHolder>>(enablePosition).isEnable = true
    adapter.notifyItemChanged(enablePosition, ADAPTER_ENABLE)
    return true
}