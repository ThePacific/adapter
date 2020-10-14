package com.pacific.adapter

import android.text.Editable
import android.widget.TextView

class SimpleAdapterTextWatcher : AdapterTextWatcher {

    override fun beforeTextChanged(
        v: TextView,
        s: CharSequence?,
        start: Int,
        count: Int,
        after: Int,
        holder: AdapterViewHolder
    ) {
    }

    override fun onTextChanged(
        v: TextView,
        s: CharSequence?,
        start: Int,
        before: Int,
        count: Int,
        holder: AdapterViewHolder
    ) {
    }

    override fun afterTextChanged(v: TextView, s: Editable, holder: AdapterViewHolder) {
    }
}