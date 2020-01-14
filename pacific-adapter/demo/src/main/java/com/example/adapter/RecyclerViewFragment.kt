package com.example.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adapter.item.RecyclerItem
import com.pacific.adapter.AdapterUtils
import com.pacific.adapter.RecyclerAdapter

class RecyclerViewFragment : Fragment() {

    private val adapter = RecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter

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

        adapter.addAll(AdapterUtils.toRecyclerItems(list))

    }

    companion object {
        fun newInstance(): RecyclerViewFragment = RecyclerViewFragment()
    }
}
