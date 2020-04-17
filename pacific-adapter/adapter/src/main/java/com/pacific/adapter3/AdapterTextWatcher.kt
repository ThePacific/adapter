package com.pacific.adapter3

import android.text.Editable
import android.widget.TextView

interface AdapterTextWatcher {

    fun beforeTextChanged(
        v: TextView,
        s: CharSequence?,
        start: Int,
        count: Int,
        after: Int,
        holder: AdapterViewHolder
    )

    fun onTextChanged(
        v: TextView,
        s: CharSequence?,
        start: Int,
        before: Int,
        count: Int,
        holder: AdapterViewHolder
    )

    fun afterTextChanged(v: TextView, s: Editable, holder: AdapterViewHolder)
}