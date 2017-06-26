package com.example.adapter

import android.arch.lifecycle.LifecycleFragment
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.adapter.databinding.FragmentListViewBinding
import com.example.adapter.item.ListItem
import com.pacific.adapter2.AbsAdapter
import com.pacific.adapter2.AdapterUtil
import com.pacific.adapter2.SimpleItem
import java.util.*

class ListViewFragment : LifecycleFragment() {
    val adapter = AbsAdapter()
    var binding: FragmentListViewBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_list_view, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.listView?.adapter = adapter
        load()
    }

    fun load() {
        var list: ArrayList<SimpleItem> = ArrayList()
        for (i in 0..10) {
            list.add(ListItem())
        }
        adapter.addAll(AdapterUtil.toItems(list))
    }

    companion object {
        fun newInstance(): ListViewFragment = ListViewFragment()
    }
}