package com.pacific.adapter

import android.widget.ImageView

interface AdapterImageLoader {

    fun load(view: ImageView, holder: AdapterViewHolder)
}