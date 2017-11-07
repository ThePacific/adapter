package com.example.adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class GridViewFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_grid_view, container, false)
    }

    companion object {
        fun newInstance(): GridViewFragment = GridViewFragment()
    }

    val isEmpty: Boolean
        get() = false

    var stringRepresentation: String? = null
        get() = this.toString()
}
