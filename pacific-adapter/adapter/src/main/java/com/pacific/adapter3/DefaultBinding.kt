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
package com.pacific.adapter3

import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.SparseArray
import android.view.View
import android.widget.*
import androidx.annotation.*

class DefaultBinding(private val itemView: View) {

    private val views: SparseArray<View> = SparseArray()

    @Suppress("UNCHECKED_CAST")
    fun <V : View> findViewById(@IdRes viewId: Int): V {
        var view = views[viewId]
        if (view == null) {
            view = itemView.findViewById(viewId)
            views.put(viewId, view)
        }
        return view as V
    }

    fun setText(@IdRes viewId: Int, value: CharSequence?): DefaultBinding {
        val view = findViewById<TextView>(viewId)
        view.text = value
        return this
    }

    fun setText(viewId: Int, @StringRes stringRes: Int): DefaultBinding {
        val view = findViewById<TextView>(viewId)
        view.setText(stringRes)
        return this
    }

    fun setImageResource(@IdRes viewId: Int, @DrawableRes imageRes: Int): DefaultBinding {
        val view = findViewById<ImageView>(viewId)
        view.setImageResource(imageRes)
        return this
    }

    fun setBackgroundColor(@IdRes viewId: Int, @ColorInt color: Int): DefaultBinding {
        val view = findViewById<View>(viewId)
        view.setBackgroundColor(color)
        return this
    }

    fun setBackgroundRes(@IdRes viewId: Int, @DrawableRes backgroundRes: Int): DefaultBinding {
        val view = findViewById<View>(viewId)
        view.setBackgroundResource(backgroundRes)
        return this
    }

    fun setTextColor(@IdRes viewId: Int, @ColorInt textColor: Int): DefaultBinding {
        val view = findViewById<TextView>(viewId)
        view.setTextColor(textColor)
        return this
    }

    fun setTextColorRes(@IdRes viewId: Int, @ColorRes textColorRes: Int): DefaultBinding {
        val view = findViewById<TextView>(viewId)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.setTextColor(itemView.context.resources.getColor(textColorRes, null))
        } else {
            view.setTextColor(itemView.context.resources.getColor(textColorRes))
        }
        return this
    }

    fun setImageDrawable(@IdRes viewId: Int, drawable: Drawable?): DefaultBinding {
        val view = findViewById<ImageView>(viewId)
        view.setImageDrawable(drawable)
        return this
    }

    fun setImageBitmap(@IdRes viewId: Int, bitmap: Bitmap?): DefaultBinding {
        val view = findViewById<ImageView>(viewId)
        view.setImageBitmap(bitmap)
        return this
    }

    fun setAlpha(
        @IdRes viewId: Int,
        @FloatRange(from = 0.0, to = 1.0) value: Float
    ): DefaultBinding {
        findViewById<View>(viewId).alpha = value
        return this
    }

    fun setVisible(@IdRes viewId: Int, visibility: Int): DefaultBinding {
        findViewById<View>(viewId).visibility = visibility
        return this
    }

    fun setTypeface(@IdRes viewId: Int, typeface: Typeface?): DefaultBinding {
        val view = findViewById<TextView>(viewId)
        view.typeface = typeface
        view.paintFlags = view.paintFlags or Paint.SUBPIXEL_TEXT_FLAG
        return this
    }

    fun setTypeface(typeface: Typeface?, @IdRes vararg viewIds: Int): DefaultBinding {
        for (viewId in viewIds) {
            val view = findViewById<TextView>(viewId)
            view.typeface = typeface
            view.paintFlags = view.paintFlags or Paint.SUBPIXEL_TEXT_FLAG
        }
        return this
    }

    fun setProgress(@IdRes viewId: Int, progress: Int): DefaultBinding {
        val view = findViewById<ProgressBar>(viewId)
        view.progress = progress
        return this
    }

    fun setProgress(@IdRes viewId: Int, progress: Int, max: Int): DefaultBinding {
        val view = findViewById<ProgressBar>(viewId)
        view.max = max
        view.progress = progress
        return this
    }

    fun setMax(@IdRes viewId: Int, max: Int): DefaultBinding {
        val view = findViewById<ProgressBar>(viewId)
        view.max = max
        return this
    }

    fun setRating(@IdRes viewId: Int, rating: Float): DefaultBinding {
        val view = findViewById<RatingBar>(viewId)
        view.rating = rating
        return this
    }

    fun setRating(@IdRes viewId: Int, rating: Float, max: Int): DefaultBinding {
        val view = findViewById<RatingBar>(viewId)
        view.max = max
        view.rating = rating
        return this
    }

    fun setTag(@IdRes viewId: Int, tag: Any?): DefaultBinding {
        val view = findViewById<View>(viewId)
        view.tag = tag
        return this
    }

    fun setTag(@IdRes viewId: Int, key: Int, tag: Any?): DefaultBinding {
        val view = findViewById<View>(viewId)
        view.setTag(key, tag)
        return this
    }

    fun setChecked(@IdRes viewId: Int, checked: Boolean): DefaultBinding {
        when (val view = findViewById<View>(viewId)) {
            is CompoundButton -> view.isChecked = checked
            is CheckedTextView -> view.isChecked = checked
            else -> (view as Checkable).isChecked = checked
        }
        return this
    }

    fun setSelect(@IdRes viewId: Int, selected: Boolean): DefaultBinding {
        val view = findViewById<View>(viewId)
        view.isSelected = selected
        return this
    }
}