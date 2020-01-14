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

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;

final class ListenerAttachImpl implements ListenerAttach {
    /**
     * listeners provider
     */
    final ListenerProvider provider;

    /**
     * holder
     */
    private final ViewHolder holder;

    private TextWatcher mTextWatcher;

    public ListenerAttachImpl(@NonNull ViewHolder holder, @NonNull ListenerProvider provider) {
        this.holder = holder;
        this.provider = provider;
    }

    /**
     * attach OnClickListener for view
     *
     * @param viewId view id
     */
    @Override
    public void attachOnClickListener(@IdRes int viewId) {
        final View.OnClickListener l = provider.getOnClickListener();
        View view = AdapterUtils.findView(holder.itemView, viewId);
        view.setTag(AdapterUtils.ADAPTER_HOLDER, holder);
        view.setOnClickListener(l);
    }

    /**
     * attach OnTouchListener for view
     *
     * @param viewId view id
     */
    @Override
    public void attachOnTouchListener(@IdRes int viewId) {
        final View.OnTouchListener l = provider.getOnTouchListener();
        View view = AdapterUtils.findView(holder.itemView, viewId);
        view.setTag(AdapterUtils.ADAPTER_HOLDER, holder);
        view.setOnTouchListener(l);
    }

    /**
     * attach OnLongClickListener for view
     *
     * @param viewId view id
     */
    @Override
    public void attachOnLongClickListener(@IdRes int viewId) {
        final View.OnLongClickListener l = provider.getOnLongClickListener();
        View view = AdapterUtils.findView(holder.itemView, viewId);
        view.setTag(AdapterUtils.ADAPTER_HOLDER, holder);
        view.setOnLongClickListener(l);
    }

    /**
     * attach CompoundButton.OnCheckedChangeListener for CompoundButton
     *
     * @param viewId CompoundButton view id
     */
    @Override
    public void attachOnCheckedChangeListener(@IdRes int viewId) {
        final CompoundButton.OnCheckedChangeListener l = provider.getOnCheckedChangeListener();
        CompoundButton view = AdapterUtils.findView(holder.itemView, viewId);
        view.setTag(AdapterUtils.ADAPTER_HOLDER, holder);
        view.setOnCheckedChangeListener(l);
    }

    /**
     * load image
     *
     * @param viewId
     */
    @Override
    public void attachImageLoader(@IdRes int viewId) {
        ImageLoader loader = provider.getImageLoader();
        if (loader != null) {
            ImageView view = AdapterUtils.findView(holder.itemView, viewId);
            loader.onImageLoad(view, holder);
        }
    }

    @Override
    public void attachTextChangedListener(@IdRes int viewId) {
        final AdapterTextWatcher l = provider.getTextChangedListener();
        if (l == null) {
            detachTextChangedListener(viewId);
            return;
        }

        final TextView view = AdapterUtils.findView(holder.itemView, viewId);
        view.setTag(AdapterUtils.ADAPTER_HOLDER, holder);
        if (mTextWatcher == null) {
            mTextWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    l.beforeTextChanged(view, s, start, count, after);
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    l.onTextChanged(view, s, start, before, count);
                }

                @Override
                public void afterTextChanged(Editable s) {
                    l.afterTextChanged(view, s);
                }
            };
        }
        view.addTextChangedListener(mTextWatcher);
    }


    @Override
    public void detachTextChangedListener(@IdRes int viewId) {
        final TextView view = AdapterUtils.findView(holder.itemView, viewId);
        if (mTextWatcher != null) {
            view.removeTextChangedListener(mTextWatcher);
        }
    }
}
