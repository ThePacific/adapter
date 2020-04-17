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

internal interface DataIO<T : RecyclerItem> {

    fun <T2 : T> get(index: Int): T2

    fun getAll(): List<T>

    fun clear()

    fun isEmpty(): Boolean

    fun contains(element: T): Boolean

    fun containsAll(list: List<T>): Boolean

    fun add(element: T)

    fun add(index: Int, element: T)

    fun addAll(list: List<T>)

    fun addAll(index: Int, list: List<T>)

    fun remove(index: Int): T

    fun remove(element: T)

    fun removeAll(list: List<T>)

    fun retainAll(list: List<T>)

    fun replace(oldElement: T, newElement: T)

    fun replaceAt(index: Int, element: T)

    fun replaceAll(list: List<T>)

    fun indexOf(element: T): Int

    fun lastIndexOf(element: T): Int

    fun subList(fromIndex: Int, toIndex: Int): List<T>

    fun replaceAll(index: Int, list: List<T>)

    fun firstSelectedIndex(): Int

    fun lastSelectedIndex(): Int

    fun firstSelectedItem(): T?

    fun lastSelectedItem(): T?

    fun selectedItems(): List<T>

    fun selectedIndices(): List<Int>
}