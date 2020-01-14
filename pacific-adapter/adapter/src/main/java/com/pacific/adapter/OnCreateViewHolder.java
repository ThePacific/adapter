package com.pacific.adapter;

import androidx.annotation.NonNull;

interface OnCreateViewHolder {

    /**
     * Note: Don't use holder's functions except holder.itemView holder.binding()
     *
     * @param holder
     * @param viewType
     */
    void onCreateViewHolder(@NonNull ViewHolder holder, int viewType);
}
