package com.pacific.app.adapter

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.pacific.adapter.AdapterImageLoader
import com.pacific.adapter.AdapterViewHolder
import com.pacific.adapter.RecyclerAdapter
import com.pacific.adapter.RecyclerItem
import com.pacific.app.adapter.base.Data
import com.pacific.app.adapter.base.GlideApp
import com.pacific.app.adapter.databinding.FragmentAboutBinding
import com.pacific.app.adapter.item.Movie
import com.pacific.app.adapter.item.MovieItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    private val adapter = RecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
        adapter.imageLoader = object : AdapterImageLoader {
            override fun load(view: ImageView, holder: AdapterViewHolder) {
                GlideApp.with(this@AboutFragment)
                    //.load(holder.item<MovieItem>().data.img)
                    .load(Uri.parse("file:///android_asset/img.jpg"))
                    .into(view)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.layoutManager = GridLayoutManager(requireActivity(), 2).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return 1
                }
            }
        }
        binding.recycler.adapter = adapter

        load()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recycler.adapter = null
        _binding = null
    }

    private fun load() {
        lifecycleScope.launch(Dispatchers.Default) {
            val list = ArrayList<RecyclerItem>().apply {
                Data.URLS.forEach {
                    add(MovieItem(Movie("Coco", "1 h 45 min", it)))
                }
            }
            withContext(Dispatchers.Main) {
                adapter.addAll(list)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = AboutFragment().apply {
            arguments = Bundle().apply {
            }
        }
    }
}
