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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.List;

/**
 * AbsAdapter for AdapterView , like ListView,GridView and Spinner
 *
 * @param <T> type Item
 */
public abstract class BaseAbsAdapter<T extends Item, H extends ViewHolder>
        extends BaseAdapter implements DataIO<T>, ListenerProvider {
    protected LayoutInflater inflater;
    protected final ArrayList<T> data;
    protected final int viewTypeCount;
    protected OnDataSetChanged onDataSetChanged;
    protected ListenerProviderImpl provider;

    /**
     * default 1 view type
     */
    public BaseAbsAdapter() {
        this(1);
    }

    public BaseAbsAdapter(int viewTypeCount) {
        this(null, viewTypeCount);
    }

    public BaseAbsAdapter(List<T> data, int viewTypeCount) {
        this.data = data == null ? new ArrayList<T>() : new ArrayList<>(data);
        this.provider = new ListenerProviderImpl();
        this.viewTypeCount = viewTypeCount;
    }

    @Override
    public int getCount() {
        return size();
    }

    @Override
    public T getItem(int position) {
        return get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return get(position).getViewType();
    }

    @Override
    public int getViewTypeCount() {
        return this.viewTypeCount;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        H holder;
        T item = getItem(position);
        if (convertView == null) {
            if (inflater == null) {
                inflater = LayoutInflater.from(parent.getContext());
            }
            convertView = inflater.inflate(item.getLayout(), parent, false);
            holder = createViewHolder(convertView);
            convertView.setTag(R.integer.adapter_holder, holder);
        } else {
            holder = (H) convertView.getTag(R.integer.adapter_holder);
        }
        holder.setCurrentPosition(position);
        holder.setSize(size());
        holder.setCurrentItem(item);
        item.bind(holder);
        return convertView;
    }

    @Override
    public boolean isEnabled(int position) {
        return position < data.size();
    }

    @Override
    public void clear() {
        if (data.size() > 0) {
            data.clear();
            notifyDataSetChanged();
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
    public boolean contains(T element) {
        return data.contains(element);
    }

    @Override
    public boolean containsAll(@NonNull List<T> list) {
        return data.containsAll(list);
    }

    @Override
    public void add(T element) {
        if (data.add(element)) {
            notifyDataSetChanged();
        }
    }

    @Override
    public void add(int index, T element) {
        final int size = data.size();
        data.add(index, element);
        if (data.size() > size) {
            notifyDataSetChanged();
        }
    }

    @Override
    public void addAll(@NonNull List<T> list) {
        if (data.addAll(list)) {
            notifyDataSetChanged();
        }
    }

    @Override
    public void addAll(int index, @NonNull List<T> list) {
        if (data.addAll(index, list)) {
            notifyDataSetChanged();
        }
    }

    @Override
    public T remove(int index) {
        T obj = data.remove(index);
        if (obj != null) {
            notifyDataSetChanged();
        }
        return obj;
    }

    @Override
    public void remove(T element) {
        if (data.remove(element)) {
            notifyDataSetChanged();
        }
    }

    @Override
    public void removeAll(@NonNull List<T> list) {
        if (data.removeAll(list)) {
            notifyDataSetChanged();
        }
    }

    @Override
    public void retainAll(@NonNull List<T> list) {
        if (data.retainAll(list)) {
            notifyDataSetChanged();
        }
    }

    @Override
    public void replaceAll(@NonNull List<T> list) {
        if (data.size() > 0) {
            data.clear();
        }
        data.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void replace(T oldElement, T newElement) {
        replaceAt(data.indexOf(oldElement), newElement);
    }

    @Override
    public void replaceAt(int index, T element) {
        T obj = data.set(index, element);
        if (obj != null) {
            notifyDataSetChanged();
        }
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
        return data.subList(fromIndex, toIndex);
    }

    @Override
    public T get(int position) {
        if (position >= data.size())
            return null;
        return data.get(position);
    }

    @Override
    public ArrayList<T> getAll() {
        return data;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
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
    public boolean hasStableIds() {
        return true;
    }

    public OnDataSetChanged getOnDataSetChanged() {
        return onDataSetChanged;
    }

    public void setOnDataSetChanged(OnDataSetChanged onDataSetChanged) {
        this.onDataSetChanged = onDataSetChanged;
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

    /**
     * create ViewHolder
     *
     * @param convertView item view
     * @return ViewHolder
     */
    protected abstract H createViewHolder(View convertView);
}