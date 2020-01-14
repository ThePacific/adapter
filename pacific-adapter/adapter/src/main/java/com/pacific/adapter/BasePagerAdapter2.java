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
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * PagerAdapter for ViewPager
 */
public abstract class BasePagerAdapter2<T extends RecyclerItem, H extends ViewHolder>
        extends PagerAdapter implements DataIO<T>, ListenerProvider {
    /**
     * data set
     */
    protected final ArrayList<T> data;
    /**
     * layout inflater
     */
    protected LayoutInflater inflater;
    /**
     * cache views
     */
    protected Queue<View> cacheViews;

    /**
     * current position
     */
    protected int currentPosition = -1;

    /**
     * current target
     */
    protected View currentTarget;

    /**
     * data set change callback
     */
    protected OnDataSetChanged onDataSetChanged;

    /**
     * listeners provider
     */
    protected ListenerProviderImpl provider;

    @NonNull
    protected OnCreateViewHolder onCreateViewHolder;

    public BasePagerAdapter2() {
        this(null);
    }

    public BasePagerAdapter2(@Nullable List<T> data) {
        this.data = data == null ? new ArrayList<T>() : new ArrayList<>(data);
        this.provider = new ListenerProviderImpl();
        this.cacheViews = new LinkedList<>();
    }

    @Override
    public void clear() {
        if (data.size() > 0) {
            data.clear();
        }
        notifyDataSetChanged();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
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
    public void add(@NonNull T element) {
        if (data.add(element)) {
            notifyDataSetChanged();
        }
    }

    @Override
    public void add(final int index, @NonNull T element) {
        int size = data.size();
        if (index >= size) {
            add(element);
        } else {
            data.add(index, element);
            if (data.size() > size) {
                notifyDataSetChanged();
            }
        }
    }

    @Override
    public void addAll(@NonNull List<T> list) {
        if (data.addAll(list)) {
            notifyDataSetChanged();
        }
    }

    @Override
    public void addAll(final int index, @NonNull List<T> list) {
        if (data.addAll(index, list)) {
            notifyDataSetChanged();
        }
    }

    @Override
    public T remove(final int index) {
        T obj = data.remove(index);
        if (obj != null) {
            notifyDataSetChanged();
        }
        return obj;
    }

    @Override
    public void remove(@NonNull T element) {
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
    public void replace(@NonNull T oldElement, @NonNull T newElement) {
        replaceAt(data.indexOf(oldElement), newElement);
    }

    @Override
    public void replaceAt(final int index, @NonNull T element) {
        T obj = data.set(index, element);
        if (obj != null) {
            notifyDataSetChanged();
        }
    }

    @Override
    public void replaceAll(final int index, @NonNull List<T> list) {
        int size = data.size();
        if (index >= size) {
            addAll(list);
        } else {
            for (int i = index; i < size; i++) {
                data.remove(index);
            }
            addAll(list);
        }
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

    @NonNull
    @Override
    public <R extends T> R get(final int index) {
        if (index >= data.size()) {
            return null;
        }
        return (R) data.get(index);
    }

    @NonNull
    @Override
    public ArrayList<T> getAll() {
        return data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        H holder;
        T item = get(position);
        View convertView = cacheViews.poll();
        if (convertView == null) {
            if (inflater == null) {
                inflater = LayoutInflater.from(container.getContext());
            }
            convertView = inflater.inflate(item.getLayout(), container, false);
            holder = createViewHolder(convertView, item.getLayout());
            convertView.setTag(AdapterUtils.ADAPTER_HOLDER, holder);
        } else {
            holder = (H) convertView.getTag(AdapterUtils.ADAPTER_HOLDER);
        }
        holder.setCurrentPosition(position);
        holder.setSize(getCount());
        holder.setCurrentItem(item);
        item.bind(holder);
        container.addView(convertView);
        return convertView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (object instanceof View) {
            View view = (View) object;
            T item = get(position);
            ViewHolder holder = (ViewHolder) view.getTag(AdapterUtils.ADAPTER_HOLDER);
            item.unbind(holder);
            container.removeView(view);
            cacheViews.add(view);
        }
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        this.currentPosition = position;
        if (object instanceof View) {
            this.currentTarget = (View) object;
        }
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (onDataSetChanged != null) {
            onDataSetChanged.apply(data.size());
        }
    }

    public OnDataSetChanged getOnDataSetChanged() {
        return onDataSetChanged;
    }

    public void setOnDataSetChanged(@Nullable OnDataSetChanged onDataSetChanged) {
        this.onDataSetChanged = onDataSetChanged;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public View getCurrentTarget() {
        return currentTarget;
    }

    @Override
    public View.OnClickListener getOnClickListener() {
        return provider.getOnClickListener();
    }

    @Override
    public void setOnClickListener(@Nullable View.OnClickListener listener) {
        provider.setOnClickListener(listener);
    }

    @Override
    public View.OnTouchListener getOnTouchListener() {
        return provider.getOnTouchListener();
    }

    @Override
    public void setOnTouchListener(@Nullable View.OnTouchListener listener) {
        provider.setOnTouchListener(listener);
    }

    @Override
    public View.OnLongClickListener getOnLongClickListener() {
        return provider.getOnLongClickListener();
    }

    @Override
    public void setOnLongClickListener(@Nullable View.OnLongClickListener listener) {
        provider.setOnLongClickListener(listener);
    }

    @Override
    public CompoundButton.OnCheckedChangeListener getOnCheckedChangeListener() {
        return provider.getOnCheckedChangeListener();
    }

    @Override
    public void setOnCheckedChangeListener(@Nullable CompoundButton.OnCheckedChangeListener listener) {
        provider.setOnCheckedChangeListener(listener);
    }

    @Override
    public AdapterTextWatcher getTextChangedListener() {
        return provider.getTextChangedListener();
    }

    @Override
    public void setTextChangedListener(@Nullable AdapterTextWatcher textWatcher) {
        provider.setTextChangedListener(textWatcher);
    }

    /**
     * get ImageLoader
     *
     * @return
     */
    @Override
    public ImageLoader getImageLoader() {
        return provider.getImageLoader();
    }

    /**
     * add image loader to load image
     *
     * @param imageLoader
     */
    @Override
    public void setImageLoader(@Nullable ImageLoader imageLoader) {
        provider.setImageLoader(imageLoader);
    }

    @NonNull
    public OnCreateViewHolder getOnCreateViewHolder() {
        return onCreateViewHolder;
    }

    public void setOnCreateViewHolder(@NonNull OnCreateViewHolder onCreateViewHolder) {
        this.onCreateViewHolder = onCreateViewHolder;
    }

    /**
     * create ViewHolder
     *
     * @param convertView item view
     * @return ViewHolder
     */
    protected abstract H createViewHolder(@NonNull View convertView, int viewType);
}