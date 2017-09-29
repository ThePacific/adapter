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

package com.pacific.adapter2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseRecyclerAdapter<T extends RecyclerItem, H extends ViewHolder>
        extends Adapter<H> implements DataIO<T>, ListenerProvider {

    /**
     * layout inflater
     */
    protected LayoutInflater inflater;

    /**
     * data set
     */
    protected final ArrayList<T> data;

    /**
     * data set change callback
     */
    protected OnDataSetChanged onDataSetChanged;

    /**
     * listeners provider
     */
    protected ListenerProviderImpl provider;

    public BaseRecyclerAdapter() {
        this(null);
    }

    public BaseRecyclerAdapter(List<T> data) {
        this.data = data == null ? new ArrayList<T>() : new ArrayList<>(data);
        this.provider = new ListenerProviderImpl();
    }

    @Override
    public int getItemViewType(int position) {
        return get(position).getLayout();
    }

    @Override
    public H onCreateViewHolder(ViewGroup parent, int viewType) {
        throw new AssertionError("You must override BaseRecyclerAdapter.onCreateViewHolder()");
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
            return;
        }
    }

    @Override
    public void onViewDetachedFromWindow(H holder) {
        int position = holder.getAdapterPosition();
        if (position != RecyclerView.NO_POSITION) {
            get(position).onViewDetachedFromWindow(holder);
            return;
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

    public void setOnDataSetChanged(OnDataSetChanged onDataSetChanged) {
        this.onDataSetChanged = onDataSetChanged;
    }

    private void onDataSetChanged() {
        if (getItemCount() == 0) {
            if (onDataSetChanged != null) {
                onDataSetChanged.onEmptyData();
            }
        } else {
            if (onDataSetChanged != null) {
                onDataSetChanged.onHasData();
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public void clear() {
        final int size = getItemCount();
        if (size > 0) {
            data.clear();
            notifyItemRangeRemoved(0, size);
            onDataSetChanged();
        }
    }

    @Override
    public void add(T element) {
        final int size = getItemCount();
        if (data.add(element)) {
            notifyItemInserted(size);
            onDataSetChanged();
        }
    }

    @Override
    public void remove(T element) {
        final int index = data.indexOf(element);
        if (data.remove(element)) {
            notifyItemRemoved(index);
            onDataSetChanged();
        }
    }

    @Override
    public boolean contains(T element) {
        return data.contains(element);
    }

    @Override
    public boolean containsAll(@NonNull List<T> list) {
        return data.containsAll(list);
    }

    @Override
    public void add(int index, T element) {
        final int size = getItemCount();
        data.add(index, element);
        if (getItemCount() > size) {
            notifyItemInserted(index);
            onDataSetChanged();
        }
    }

    @Override
    public void addAll(@NonNull List<T> list) {
        final int size = getItemCount();
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
        final int size = getItemCount();
        int index;
        List<Integer> indexes = new ArrayList<>();
        for (T item : list) {
            index = data.indexOf(item);
            if (index >= 0) {
                indexes.add(index);
            }
        }
        if (data.removeAll(list)) {
            for (int i = 0; i < size; i++) {
                if (indexes.contains(i)) {
                    notifyItemRemoved(i);
                }
            }
            onDataSetChanged();
        }
    }

    @Override
    public void retainAll(@NonNull List<T> list) {
        final int size = getItemCount();
        List<Integer> indexes = new ArrayList<>();
        for (T item : list) {
            indexes.add(data.indexOf(item));
        }
        if (data.retainAll(list)) {
            for (int i = 0; i < size; i++) {
                if (!indexes.contains(i)) {
                    notifyItemRemoved(i);
                }
            }
            onDataSetChanged();
        }
    }

    @Override
    public List<T> getAll() {
        return data;
    }

    @Override
    public <R extends T> R get(int index) {
        if (index >= data.size()) {
            return null;
        }
        return (R) data.get(index);
    }

    @Override
    public void replaceAt(int index, T element) {
        if (data.set(index, element) != null) {
            notifyItemChanged(index);
        }
    }

    @Override
    public void replace(T oldElement, T newElement) {
        final int index = data.indexOf(oldElement);
        if (data.set(index, newElement) != null) {
            notifyItemChanged(index);
        }
    }

    @Override
    public void replaceAll(@NonNull List<T> list) {
        final int size = getItemCount();
        if (getItemCount() > 0) {
            data.clear();
            notifyItemRangeRemoved(0, size);
        }
        if (data.addAll(list)) {
            notifyItemRangeInserted(0, list.size());
        }
        onDataSetChanged();
    }

    @Override
    public void replaceAll(int index, @NonNull List<T> list) {
        final int size = getItemCount();
        for (int i = index; i < size; i++) {
            data.remove(i);
        }
        notifyItemRangeRemoved(index, size - index);
        if (data.addAll(list)) {
            notifyItemRangeInserted(index, list.size());
        }
        onDataSetChanged();
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
    public int indexOf(T element) {
        return data.indexOf(element);
    }

    @Override
    public int lastIndexOf(T element) {
        return data.lastIndexOf(element);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex >= getItemCount()) {
            return Collections.emptyList();
        }
        return data.subList(fromIndex, toIndex);
    }

    @Override
    public void setOnClickListener(View.OnClickListener listener) {
        provider.setOnClickListener(listener);
    }

    @Override
    public View.OnClickListener getOnClickListener() {
        return provider.getOnClickListener();
    }

    @Override
    public void setOnTouchListener(View.OnTouchListener listener) {
        provider.setOnTouchListener(listener);
    }

    @Override
    public View.OnTouchListener getOnTouchListener() {
        return provider.getOnTouchListener();
    }

    @Override
    public void setOnLongClickListener(View.OnLongClickListener listener) {
        provider.setOnLongClickListener(listener);
    }

    @Override
    public View.OnLongClickListener getOnLongClickListener() {
        return provider.getOnLongClickListener();
    }

    @Override
    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener listener) {
        provider.setOnCheckedChangeListener(listener);
    }

    @Override
    public CompoundButton.OnCheckedChangeListener getOnCheckedChangeListener() {
        return provider.getOnCheckedChangeListener();
    }

    /**
     * add image loader to load image
     */
    @Override
    public void setImageLoader(ImageLoader imageLoader) {
        provider.setImageLoader(imageLoader);
    }

    /**
     * get ImageLoader
     */
    @Override
    public ImageLoader getImageLoader() {
        return provider.getImageLoader();
    }
}
