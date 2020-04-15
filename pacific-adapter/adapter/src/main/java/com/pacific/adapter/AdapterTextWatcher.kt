package com.pacific.adapter

import android.text.Editable
import android.widget.TextView

interface AdapterTextWatcher {

    fun beforeTextChanged(
        v: TextView,
        s: CharSequence?,
        start: Int,
        count: Int,
        after: Int,
        holder: ViewHolder
    )

    fun onTextChanged(
        v: TextView,
        s: CharSequence?,
        start: Int,
        before: Int,
        count: Int,
        holder: ViewHolder
    )

    fun afterTextChanged(v: TextView, s: Editable, holder: ViewHolder)
}