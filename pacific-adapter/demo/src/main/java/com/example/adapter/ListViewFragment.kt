package com.example.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.adapter.databinding.FragmentListViewBinding
import com.example.adapter.item.ListItem
import com.pacific.adapter.AbsAdapter
import com.pacific.adapter.AdapterUtils

class ListViewFragment : Fragment() {
    val adapter = AbsAdapter()
    var binding: FragmentListViewBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_view, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.listView?.adapter = adapter
        load()
    }

    private fun load() {
        var list: ArrayList<ListItem> = ArrayList()
        for (i in 0..10) {
            list.add(ListItem())
        }
        adapter.addAll(AdapterUtils.toRecyclerItems(list))
    }

    companion object {
        fun newInstance(): ListViewFragment = ListViewFragment()
    }
}