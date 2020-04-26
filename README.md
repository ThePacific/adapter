# Adapter
A quick adapter library for RecyclerView, GridView, ListView, ViewPager, Spinner. It abstracts the boilerplate of item view types, item layouts, viewholders, span sizes , and more, in order to simplify building complex screens with multiple view types.

If you are using version 2.x please check [here](https://github.com/thepacific/adapter/blob/master/README-2x.md)

[![Download](https://img.shields.io/maven-central/v/com.github.thepacific/adapter.svg)](https://search.maven.org/artifact/com.github.thepacific/adapter)[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Adapter-green.svg?style=true)](https://android-arsenal.com/details/1/3449)

![](https://github.com/thepacific/adapter/blob/master/previews/preview0.gif)

# Features
+ Support ViewBinding、DataBinding、DefaultBinding.
+ Multiple view types without any ViewHolder
+ Simple, flexible

# Setup
```groovy
compile 'com.github.thepacific:adapter:3.0.0'
```

# Item
Extend [SimpleRecylcerItem](https://github.com/thepacific/adapter/blob/master/pacific-adapter/adapter/src/main/java/com/pacific/adapter3/SimpleRecyclerItem.kt). Optionally, you may implement [RecyclerItem](https://github.com/thepacific/adapter/blob/master/pacific-adapter/adapter/src/main/java/com/pacific/adapter3/RecyclerItem.kt)

```kotlin
class MovieItem(val data: Movie) : SimpleRecyclerItem() {

    override fun getLayout(): Int {
        return R.layout.item_movie
    }

    override fun bind(holder: AdapterViewHolder) {
        val binding = holder.binding(ItemMovieBinding::bind)
        binding.itemPosterTitle.text = data.name
        binding.itemPosterRunningTime.text = data.showTime

        // attach UI listeners
        holder.attachOnClickListener(R.id.item_poster_post)
        holder.attachOnLongClickListener(R.id.item_poster_post)
        holder.attachOnCheckedChangeListener(R.id.item_poster_post)
        holder.attachOnTouchListener(R.id.item_poster_post)
        holder.attachImageLoader(R.id.item_poster_post)
        holder.attachTextChangedListener(R.id.item_poster_post)
        holder.detachTextChangedListener(R.id.item_poster_post)
    }

    override fun bindPayloads(holder: AdapterViewHolder, payloads: List<Any>?) {
    }

    override fun unbind(holder: AdapterViewHolder) {
    }

    // You may override other methods
}
```

# Listeners
Set OnClickListener、OnLongClickListener、OnTouchListener、OnCheckedChangeListener、ImageLoader and TextWatcher:

```kotlin
class HomeFragment : Fragment() {

    private val adapter = RecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }

        // adapter.setOnTouchListener(OnTouchListener listener);
        // adapter.setOnLongClickListener(OnLongClickListener listener);
        // adapter.setOnCheckedChangeListener(OnCheckedChangeListener listener);
        adapter.onClickListener = View.OnClickListener { v ->

            // AdapterUtils
            val holder: AdapterViewHolder = getHolder(v)// get ViewHolder
            val v = holder.itemView.findViewById<TextView>(R.id.item_poster_title)// find view
            val item: MovieItem = holder.item()// get Item
            val size: Int = holder.size// get adapter data size
            val position = holder.adapterPosition // get item position
        }

        // load image
        adapter.imageLoader = object : AdapterImageLoader {

            override fun onImageLoad(imageView: ImageView, holder: AdapterViewHolder) {

                GlideApp.with(this@HomeFragment)
                    .load(holder.item<MovieItem>().data.img)
                    .into(imageView)
            }
        }

        // TextWatcher
        adapter.textChangedListener = object : AdapterTextWatcher {

            override fun beforeTextChanged(
                v: TextView,
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int,
                holder: AdapterViewHolder
            ) {
                // AdapterUtils
            }

            override fun onTextChanged(
                v: TextView,
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int,
                holder: AdapterViewHolder
            ) {
                // AdapterUtils
            }

            override fun afterTextChanged(v: TextView, s: Editable, holder: AdapterViewHolder) {
                // AdapterUtils
            }
        }
    }
}
```

# Others
```kotlin
// data set changed callback , it's useful to show or hide empty view
adapter.setOnDataSetChanged(OnDataSetChanged onDataSetChanged);
```

# License  
[The MIT License ](https://opensource.org/licenses/MIT)
