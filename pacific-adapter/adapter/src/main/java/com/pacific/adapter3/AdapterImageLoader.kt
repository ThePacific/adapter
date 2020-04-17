package com.pacific.adapter3

import android.widget.ImageView

interface AdapterImageLoader {

    fun onImageLoad(imageView: ImageView, holder: AdapterViewHolder)
}