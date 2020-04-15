package com.pacific.adapter

import android.widget.ImageView

interface AdapterImageLoader {

    fun onImageLoad(imageView: ImageView, holder: ViewHolder)
}