package com.example.adapter

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.adapter.databinding.FragmentListViewBinding
import com.example.adapter.item.ListItem
import com.pacific.adapter.AbsAdapter
import com.pacific.adapter.AdapterUtil

class ListViewFragment : Fragment() {
    val adapter = AbsAdapter()
    var binding: FragmentListViewBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_view, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.listView?.adapter = adapter
        load()
        var obj: ListItem = adapter.get(0)
        Log.e("____________", obj.toString())
    }

    fun load() {
        var list: ArrayList<ListItem> = ArrayList()
        for (i in 0..10) {
            list.add(ListItem())
        }
        adapter.addAll(AdapterUtil.toItems(list))
    }

    companion object {
        fun newInstance(): ListViewFragment = ListViewFragment()
    }
}