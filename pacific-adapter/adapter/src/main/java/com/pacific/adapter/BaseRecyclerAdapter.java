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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseRecyclerAdapter<T extends RecyclerItem, H extends ViewHolder>
        extends Adapter<H>
        implements DataIO<T>, ListenerProvider {

    /**
     * data set
     */
    @NonNull
    protected final ArrayList<T> data;
    /**
     * layout inflater
     */
    @Nullable
    protected LayoutInflater inflater;
    /**
     * data set change callback
     */
    @Nullable
    protected OnDataSetChanged onDataSetChanged;

    /**
     * listeners provider
     */
    @Nullable
    protected ListenerProviderImpl provider;

    @NonNull
    protected OnCreateViewHolder onCreateViewHolder;

    public BaseRecyclerAdapter() {
        this(null);
    }

    public BaseRecyclerAdapter(@Nullable List<T> data) {
        this.data = data == null ? new ArrayList<T>() : new ArrayList<>(data);
        this.provider = new ListenerProviderImpl();
    }

    @Override
    public int getItemViewType(int position) {
        return get(position).getLayout();
    }

    @Override
    public H onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        throw new AssertionError(
                "You must override BaseRecyclerAdapter.onCreateViewHolder()"
        );
    }

    @Override
    public void onViewRecycled(H holder) {
        int position = holder.getAdapterPosition();
        if (position != RecyclerView.NO_POSITION) {
            get(position).unbind(holder);
            return;
        }
        super.onViewRecycled(holder);
    }

    @Override
    public void onViewAttachedToWindow(H holder) {
        int position = holder.getAdapterPosition();
        if (position != RecyclerView.NO_POSITION) {
            get(position).onViewAttachedToWindow(holder);
        }
    }

    @Override
    public void onViewDetachedFromWindow(H holder) {
        int position = holder.getAdapterPosition();
        if (position != RecyclerView.NO_POSITION) {
            get(position).onViewDetachedFromWindow(holder);
        }
    }

    @Override
    public void onBindViewHolder(H holder, int position) {
        T item = get(position);
        holder.setCurrentPosition(position);
        holder.setSize(getItemCount());
        holder.setCurrentItem(item);
        item.bind(holder);
    }

    @Override
    public void onBindViewHolder(H holder, int position, List<Object> payloads) {
        if (payloads == null || payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
        } else {
            T item = get(position);
            holder.setCurrentPosition(position);
            holder.setSize(getItemCount());
            holder.setCurrentItem(item);
            item.bindPayloads(holder, payloads);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public OnDataSetChanged getOnDataSetChanged() {
        return onDataSetChanged;
    }

    public void setOnDataSetChanged(@Nullable OnDataSetChanged onDataSetChanged) {
        this.onDataSetChanged = onDataSetChanged;
    }

    private void onDataSetChanged() {
        if (onDataSetChanged != null) {
            onDataSetChanged.apply(data.size());
        }
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public void clear() {
        if (data.size() > 0) {
            data.clear();
            notifyDataSetChanged();
        }
        onDataSetChanged();
    }

    @Override
    public void add(@NonNull T element) {
        int size = data.size();
        if (data.add(element)) {
            notifyItemInserted(size);
            onDataSetChanged();
        }
    }

    @Override
    public void remove(@NonNull T element) {
        int index = data.indexOf(element);
        if (data.remove(element)) {
            notifyItemRemoved(index);
            onDataSetChanged();
        }
    }

    @Override
    public boolean contains(@NonNull T element) {
        return data.contains(element);
    }

    @Override
    public boolean containsAll(@NonNull List<T> list) {
        return data.containsAll(list);
    }

    @Override
    public void add(int index, @NonNull T element) {
        int size = getItemCount();
        if (index >= size) {
            throw new AssertionError("index >= size");
        }
        data.add(index, element);
        if (data.size() > size) {
            notifyItemInserted(index);
            onDataSetChanged();
        }
    }

    @Override
    public void addAll(@NonNull List<T> list) {
        int size = data.size();
        if (data.addAll(list)) {
            notifyItemRangeInserted(size, list.size());
            onDataSetChanged();
        }
    }

    @Override
    public void addAll(int index, @NonNull List<T> list) {
        if (data.addAll(index, list)) {
            notifyItemRangeInserted(index, list.size());
            onDataSetChanged();
        }
    }

    @Override
    public void removeAll(@NonNull List<T> list) {
        List<Integer> indexes = new ArrayList<>();
        int index;
        for (T item : list) {
            index = data.indexOf(item);
            if (index >= 0) {
                indexes.add(index);
            }
        }
        if (data.removeAll(list)) {
            for (int k = 0; k < indexes.size(); k++) {
                notifyItemRemoved(indexes.get(k));
            }
            onDataSetChanged();
        }
    }

    @Override
    public void retainAll(@NonNull List<T> list) {
        List<Integer> indexes = new ArrayList<>();
        int index;
        for (T item : list) {
            index = data.indexOf(item);
            if (index >= 0) {
                indexes.add(index);
            }
        }

        int size = data.size();
        if (data.retainAll(list)) {
            for (int i = 0; i < size; i++) {
                if (!indexes.contains(i)) {
                    notifyItemRemoved(i);
                }
            }
            onDataSetChanged();
        }
    }

    @NonNull
    @Override
    public List<T> getAll() {
        return data;
    }

    @Override
    public <R extends T> R get(int index) {
        if (index >= data.size()) {
            throw new AssertionError("index >= size");
        }
        return (R) data.get(index);
    }

    @Override
    public void replaceAt(int index, @NonNull T element) {
        if (data.set(index, element) != null) {
            notifyItemChanged(index);
        }
    }

    @Override
    public void replace(@NonNull T oldElement, @NonNull T newElement) {
        replaceAt(data.indexOf(oldElement), newElement);
    }

    @Override
    public void replaceAll(@NonNull List<T> list) {
        if (data.size() > 0) {
            data.clear();
        }
        data.addAll(list);
        notifyDataSetChanged();
        onDataSetChanged();
    }

    @Override
    public void replaceAll(int index, @NonNull List<T> list) {
        replaceAll(index, list, false);
    }

    public final void replaceAll(
            int index,
            @NonNull List<T> list,
            boolean notifyDataSetChanged
    ) {
        int size = data.size();
        if (index >= size) {
            addAll(list);
        } else {
            for (int i = index; i < size; i++) {
                data.remove(index);
            }
            if (!notifyDataSetChanged) {
                notifyItemRangeRemoved(index, size - index);
            }
            if (data.addAll(list) && !notifyDataSetChanged) {
                notifyItemRangeInserted(index, list.size());
            }
            if (notifyDataSetChanged) {
                notifyDataSetChanged();
            }
            onDataSetChanged();
        }
    }

    @Override
    public T remove(int index) {
        T obj = data.remove(index);
        if (obj != null) {
            notifyItemRemoved(index);
            onDataSetChanged();
        }
        return obj;
    }

    @Override
    public int indexOf(@NonNull T element) {
        return data.indexOf(element);
    }

    @Override
    public int lastIndexOf(@NonNull T element) {
        return data.lastIndexOf(element);
    }

    @NonNull
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex >= getItemCount()) {
            return Collections.emptyList();
        }
        return data.subList(fromIndex, toIndex);
    }

    @Override
    public int firstSelectedIndex() {
        return AdapterUtils.firstSelectedIndex(data);
    }

    @Override
    public int lastSelectedIndex() {
        return AdapterUtils.lastSelectedIndex(data);
    }

    @Override
    @NonNull
    public List<Integer> selectedIndices() {
        return AdapterUtils.selectedIndices(data);
    }

    @Override
    @Nullable
    public T firstSelectedItem() {
        return AdapterUtils.firstSelectedItem(data);
    }

    @Override
    @Nullable
    public T lastSelectedItem() {
        return AdapterUtils.lastSelectedItem(data);
    }

    @Override
    @NonNull
    public List<T> selectedItems() {
        return AdapterUtils.selectedItems(data);
    }

    @Override
    @Nullable
    public View.OnClickListener getOnClickListener() {
        return provider.getOnClickListener();
    }

    @Override
    public void setOnClickListener(@Nullable View.OnClickListener listener) {
        provider.setOnClickListener(listener);
    }

    @Override
    @Nullable
    public View.OnTouchListener getOnTouchListener() {
        return provider.getOnTouchListener();
    }

    @Override
    public void setOnTouchListener(@Nullable View.OnTouchListener listener) {
        provider.setOnTouchListener(listener);
    }

    @Override
    @Nullable
    public View.OnLongClickListener getOnLongClickListener() {
        return provider.getOnLongClickListener();
    }

    @Override
    public void setOnLongClickListener(@Nullable View.OnLongClickListener listener) {
        provider.setOnLongClickListener(listener);
    }

    @Override
    @Nullable
    public CompoundButton.OnCheckedChangeListener getOnCheckedChangeListener() {
        return provider.getOnCheckedChangeListener();
    }

    @Override
    public void setOnCheckedChangeListener(@Nullable CompoundButton.OnCheckedChangeListener listener) {
        provider.setOnCheckedChangeListener(listener);
    }

    /**
     * get ImageLoader
     */
    @Override
    @Nullable
    public ImageLoader getImageLoader() {
        return provider.getImageLoader();
    }

    /**
     * add image loader to load image
     */
    @Override
    public void setImageLoader(@Nullable ImageLoader imageLoader) {
        provider.setImageLoader(imageLoader);
    }

    @Override
    @Nullable
    public AdapterTextWatcher getTextChangedListener() {
        return provider.getTextChangedListener();
    }

    @Override
    public void setTextChangedListener(@Nullable AdapterTextWatcher textWatcher) {
        provider.setTextChangedListener(textWatcher);
    }

    @NonNull
    public OnCreateViewHolder getOnCreateViewHolder() {
        return onCreateViewHolder;
    }

    public void setOnCreateViewHolder(@NonNull OnCreateViewHolder onCreateViewHolder) {
        this.onCreateViewHolder = onCreateViewHolder;
    }
}
