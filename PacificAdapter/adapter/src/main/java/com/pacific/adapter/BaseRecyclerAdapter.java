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

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.util.ListUpdateCallback;
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

    protected LayoutInflater inflater;
    protected final ArrayList<T> data;
    protected int flagPosition = -1;
    protected OnDataSetChanged onDataSetChanged;
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
        flagPosition = position;
        return get(position).getViewType();
    }

    @Override
    public H onCreateViewHolder(ViewGroup parent, int viewType) {
        throw new AssertionError("You must override BaseRecyclerAdapter.onCreateViewHolder()");
    }

    @Override
    public void onViewRecycled(H holder) {
        int position = holder.getAdapterPosition();
        if (position != RecyclerView.NO_POSITION) {
            H helper = (H) holder.itemView.getTag(R.integer.adapter_holder);
            get(position).unbind(helper);
            return;
        }
        super.onViewRecycled(holder);
    }

    @Override
    public void onViewAttachedToWindow(H holder) {
        int position = holder.getAdapterPosition();
        if (position != RecyclerView.NO_POSITION) {
            H helper = (H) holder.itemView.getTag(R.integer.adapter_holder);
            get(position).onViewAttachedToWindow(helper);
            return;
        }
    }

    @Override
    public void onViewDetachedFromWindow(H holder) {
        int position = holder.getAdapterPosition();
        if (position != RecyclerView.NO_POSITION) {
            H helper = (H) holder.itemView.getTag(R.integer.adapter_holder);
            get(position).onViewDetachedFromWindow(helper);
            return;
        }
    }

    @Override
    public boolean onFailedToRecycleView(H holder) {
        int position = holder.getAdapterPosition();
        if (position != RecyclerView.NO_POSITION) {
            return get(position).isRecyclable();
        }
        return super.onFailedToRecycleView(holder);
    }

    @Override
    public void onBindViewHolder(H holder, int position) {
    }

    @Override
    public void onBindViewHolder(H holder, int position, List<Object> payloads) {
        T item = get(position);
        holder.setCurrentPosition(position);
        holder.setSize(size());
        holder.setCurrentItem(item);
        if (payloads.isEmpty()) {
            item.bind(holder);
        } else {
            item.bindPayloads(holder, payloads);
        }
    }

    @Override
    public int getItemCount() {
        return size();
    }

    public OnDataSetChanged getOnDataSetChanged() {
        return onDataSetChanged;
    }

    public void setOnDataSetChanged(OnDataSetChanged onDataSetChanged) {
        this.onDataSetChanged = onDataSetChanged;
    }

    private void onDataSetChanged() {
        if (size() <= 0) {
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
    public int size() {
        return data.size();
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
            onDataSetChanged();
        }
    }

    @Override
    public void add(T element) {
        final int size = data.size();
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
        final int size = data.size();
        data.add(index, element);
        if (data.size() > size) {
            notifyItemInserted(index);
            onDataSetChanged();
        }
    }

    @Override
    public void addAll(@NonNull List<T> list) {
        final int size = data.size();
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
        final int size = data.size();
        List<Integer> indexes = new ArrayList<>();
        for (T item : list) indexes.add(data.indexOf(item));
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
        final int size = data.size();
        List<Integer> indexes = new ArrayList<>();
        for (T item : list) indexes.add(data.indexOf(item));
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
    public T get(int index) {
        return data.get(index);
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
        if (data.size() == 0) {
            addAll(list);
            return;
        }
        if (list == null || list.size() == 0) {
            clear();
            return;
        }
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new UpdatingCallback(list));
        data.clear();
        data.addAll(list);
        diffResult.dispatchUpdatesTo(listUpdateCallback);
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
        if (fromIndex < 0 || toIndex >= data.size()) {
            return Collections.emptyList();
        }
        return data.subList(fromIndex, toIndex);
    }

    @Override
    public void clearListeners() {
        provider.clearListeners();
    }

    @Override
    public void addOnClickListener(@LayoutRes int layout, View.OnClickListener listener) {
        provider.addOnClickListener(layout, listener);
    }

    @Override
    public View.OnClickListener getOnClickListener(@LayoutRes int layout) {
        return provider.getOnClickListener(layout);
    }

    @Override
    public void addOnTouchListener(@LayoutRes int layout, View.OnTouchListener listener) {
        provider.addOnTouchListener(layout, listener);
    }

    @Override
    public View.OnTouchListener getOnTouchListener(@LayoutRes int layout) {
        return provider.getOnTouchListener(layout);
    }

    @Override
    public void addOnLongClickListener(@LayoutRes int layout, View.OnLongClickListener listener) {
        provider.addOnLongClickListener(layout, listener);
    }

    @Override
    public View.OnLongClickListener getOnLongClickListener(@LayoutRes int layout) {
        return provider.getOnLongClickListener(layout);
    }

    @Override
    public void addOnCheckedChangeListener(@LayoutRes int layout,
                                           CompoundButton.OnCheckedChangeListener listener) {
        provider.addOnCheckedChangeListener(layout, listener);
    }

    @Override
    public CompoundButton.OnCheckedChangeListener getOnCheckedChangeListener(@LayoutRes int layout) {
        return provider.getOnCheckedChangeListener(layout);
    }

    private ListUpdateCallback listUpdateCallback = new ListUpdateCallback() {
        @Override
        public void onInserted(int position, int count) {
            notifyItemRangeInserted(position, count);
        }

        @Override
        public void onRemoved(int position, int count) {
            notifyItemRangeRemoved(position, count);
        }

        @Override
        public void onMoved(int fromPosition, int toPosition) {
            notifyItemMoved(fromPosition, toPosition);
        }

        @Override
        public void onChanged(int position, int count, Object payload) {
            notifyItemRangeChanged(position, count);
        }
    };

    private class UpdatingCallback extends DiffUtil.Callback {

        private List<T> newList;

        UpdatingCallback(List<T> newList) {
            this.newList = newList;
        }

        @Override
        public int getOldListSize() {
            return data.size();
        }

        @Override
        public int getNewListSize() {
            return newList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            T oldItem = data.get(oldItemPosition);
            T newItem = newList.get(newItemPosition);
            if (oldItem.getLayout() != newItem.getLayout()) {
                return false;
            }
            return oldItem.diffId() == newItem.diffId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            T oldItem = data.get(oldItemPosition);
            T newItem = newList.get(newItemPosition);
            return oldItem.equals(newItem);
        }
    }
}
