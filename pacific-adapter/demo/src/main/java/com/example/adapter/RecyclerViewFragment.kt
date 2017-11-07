package com.example.adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.adapter.item.RecyclerItem
import com.pacific.adapter.AdapterUtil
import com.pacific.adapter.RecyclerAdapter

class RecyclerViewFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var rv: RecyclerView = view!!.findViewById<RecyclerView>(R.id.recycler)
        rv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        val adapter = RecyclerAdapter()
        rv.adapter = adapter

        val list: ArrayList<RecyclerItem> = ArrayList()
        list.add(RecyclerItem())
        list.add(RecyclerItem())
        list.add(RecyclerItem())
        list.add(RecyclerItem())
        list.add(RecyclerItem())
        list.add(RecyclerItem())
        list.add(RecyclerItem())
        list.add(RecyclerItem())
        list.add(RecyclerItem())
        list.add(RecyclerItem())
        list.add(RecyclerItem())
        list.add(RecyclerItem())
        list.add(RecyclerItem())
        list.add(RecyclerItem())

        adapter.addAll(AdapterUtil.toRecyclerItems(list))

    }

    companion object {
        fun newInstance(): RecyclerViewFragment = RecyclerViewFragment()
    }
}
