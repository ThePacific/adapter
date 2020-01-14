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

package com.pacific.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * insert, remove, replace, get
 *
 * @param <T> type of data
 */
public interface DataIO<T extends RecyclerItem> {

    /**
     * clean data
     */
    void clear();

    /**
     * @return true when size is 0
     */
    boolean isEmpty();

    /**
     * if containing item
     *
     * @param element item
     * @return true or false
     */
    boolean contains(@NonNull T element);

    /**
     * if containing the list
     *
     * @param list data list
     * @return true or false
     */
    boolean containsAll(@NonNull List<T> list);

    /**
     * add item
     *
     * @param element item
     */
    void add(@NonNull T element);

    /**
     * add item at special index
     *
     * @param index   index
     * @param element item
     */
    void add(final int index, @NonNull T element);

    /**
     * append list
     *
     * @param list data list
     */
    void addAll(@NonNull List<T> list);

    /**
     * insert list at special index
     *
     * @param index index
     * @param list  data list
     */
    void addAll(final int index, @NonNull List<T> list);

    /**
     * remove item from index
     *
     * @param index target index
     * @return item removed
     */
    T remove(final int index);

    /**
     * remove item
     *
     * @param element item
     */
    void remove(@NonNull T element);

    /**
     * clean items contained by list
     *
     * @param list list
     */
    void removeAll(@NonNull List<T> list);

    /**
     * retain the target list and remove others not in the target list
     *
     * @param list retained list
     */
    void retainAll(@NonNull List<T> list);

    /**
     * replace special item
     *
     * @param oldElement old item
     * @param newElement new item
     */
    void replace(@NonNull T oldElement, @NonNull T newElement);

    /**
     * replace special item
     */
    void replaceAt(final int index, @NonNull T element);

    /**
     * clean source data list and then add new list
     *
     * @param list new list
     */
    void replaceAll(@NonNull List<T> list);

    /**
     * get first position of item
     *
     * @param element item
     * @return first position of item
     */
    int indexOf(@NonNull T element);

    /**
     * get last position of item
     *
     * @param element item
     * @return last position of item
     */
    int lastIndexOf(@NonNull T element);

    /**
     * get data item from index
     *
     * @param index item index
     * @return data item
     */
    @NonNull
    <R extends T> R get(final int index);

    /**
     * @return the whole data list
     */
    @NonNull
    List<T> getAll();

    /**
     * get sub list of current data list
     *
     * @param fromIndex beginning index
     * @param toIndex   exclude index
     * @return sub lists
     */
    @NonNull
    List<T> subList(int fromIndex, int toIndex);

    /**
     * clean source data list and then add new list
     *
     * @param index from index
     * @param list  new list
     */
    void replaceAll(final int index, @NonNull List<T> list);

    int firstSelectedIndex();

    int lastSelectedIndex();

    @NonNull
    List<Integer> selectedIndices();

    @Nullable
    T firstSelectedItem();

    @Nullable
    T lastSelectedItem();

    @NonNull
    List<T> selectedItems();
}