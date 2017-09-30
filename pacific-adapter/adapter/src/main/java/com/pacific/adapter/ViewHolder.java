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

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ViewHolder extends RecyclerView.ViewHolder implements ListenerAttach {

    /**
     * binding
     */
    private Object binding;
    /**
     * adapter position
     */
    private int position = -1;

    /**
     * adapter size
     */
    private int size = 0;

    /**
     * item
     */
    private Item item;

    /**
     * ListenerAttach
     */
    private final ListenerAttachImpl listenerAttach;

    public ViewHolder(View itemView, ListenerProvider provider) {
        super(itemView);
        this.listenerAttach = new ListenerAttachImpl(provider, this);
        try {
            Class<?> clazz = Class.forName("android.databinding.DataBindingUtil");
            Method method = clazz.getMethod("bind", View.class);
            binding = method.invoke(null, itemView);
        } catch (ClassNotFoundException e) {
            binding = new DefaultBinding(itemView);
        } catch (NoClassDefFoundError error) {
            binding = new DefaultBinding(itemView);
        } catch (NoSuchMethodException e) {
            binding = new DefaultBinding(itemView);
        } catch (IllegalAccessException e) {
            binding = new DefaultBinding(itemView);
        } catch (InvocationTargetException e) {
            binding = new DefaultBinding(itemView);
        } catch (IllegalArgumentException e) {
            binding = new DefaultBinding(itemView);
        }
    }

    /**
     * get adapter size
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * get item
     *
     * @return
     */
    public <V extends Item> V getItem() {
        return (V) item;
    }

    /**
     * is first item
     *
     * @return
     */
    public boolean isFirstItem() {
        if (position == 0) return true;
        return false;
    }

    /**
     * is last item
     *
     * @return
     */
    public boolean isLastItem() {
        if (position == size - 1) return true;
        return false;
    }

    /**
     * get adapter position , just used in ListView,GridView,Spinner,ViewPager
     *
     * @return
     */
    public int getCurrentPosition() {
        return position;
    }

    void setCurrentPosition(int position) {
        this.position = position;
    }

    void setSize(int size) {
        this.size = size;
    }

    void setCurrentItem(Item item) {
        this.item = item;
    }

    /**
     * get binding,if you are using DataBinding , it will return ViewDataBinding,
     * or it will return {@link DefaultBinding}
     *
     * @param <T>
     * @return
     */
    public <T extends Object> T binding() {
        return (T) this.binding;
    }

    /**
     * attach OnClickListener for view
     *
     * @param viewId view id
     */
    @Override
    public void attachOnClickListener(@IdRes int viewId) {
        listenerAttach.attachOnClickListener(viewId);
    }

    /**
     * attach OnTouchListener for view
     *
     * @param viewId view id
     */
    @Override
    public void attachOnTouchListener(@IdRes int viewId) {
        listenerAttach.attachOnTouchListener(viewId);
    }

    /**
     * attach OnLongClickListener for view
     *
     * @param viewId view id
     */
    @Override
    public void attachOnLongClickListener(@IdRes int viewId) {
        listenerAttach.attachOnLongClickListener(viewId);
    }

    /**
     * attach CompoundButton.OnCheckedChangeListener for CompoundButton
     *
     * @param viewId CompoundButton view id
     */
    @Override
    public void attachOnCheckedChangeListener(@IdRes int viewId) {
        listenerAttach.attachOnCheckedChangeListener(viewId);
    }

    @Override
    public void attachImageLoader(@IdRes int viewId) {
        listenerAttach.attachImageLoader(viewId);
    }
}
