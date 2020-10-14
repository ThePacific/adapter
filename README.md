# Adapter
A quick adapter library for RecyclerView, GridView, ListView, ViewPager, Spinner. It abstracts the boilerplate of item view types, item layouts, viewholders, span sizes , and more, in order to simplify building complex screens with multiple view types.

If you are using version 2.x please check [here](https://github.com/thepacific/adapter/blob/master/README-2x.md)

[![Download](https://img.shields.io/maven-central/v/com.github.thepacific/adapter.svg)](https://search.maven.org/artifact/com.github.thepacific/adapter)[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Adapter-green.svg?style=true)](https://android-arsenal.com/details/1/3449)

![](https://github.com/thepacific/adapter/blob/master/previews/preview01.png)

# Features
+ Support ViewBinding、DataBinding、DefaultBinding.
+ Multiple view types without any ViewHolder
+ Simple, flexible

# Setup
```groovy
implementation 'com.github.thepacific:adapter:{lastVersion}'
```

# Usage

```kotlin
class MyType1(val data: Any) : SimpleRecyclerItem()

class MyType2(val data: Any) : SimpleRecyclerItem()

class MyType3(val data: Any) : SimpleRecyclerItem()

val adapter = RecyclerAdapter()

adapter.onClickListener = View.OnClickListener { }
adapter.imageLoader = adapter.imageLoader = object : AdapterImageLoader {}

val item1 = MyType1(Any())
val list2 = mutableListOf<MyType2>()
val list3 = mutableListOf<MyType3>()

adapter.add(item1)
adapter.addAll(list2.toList())
adapter.addAll(list3.toList())
```

# Item
Extend [SimpleRecylcerItem](https://github.com/thepacific/adapter/blob/master/pacific-adapter/adapter/src/main/java/com/pacific/adapter/SimpleRecyclerItem.kt). Optionally, you may implement [RecyclerItem](https://github.com/thepacific/adapter/blob/master/pacific-adapter/adapter/src/main/java/com/pacific/adapter/RecyclerItem.kt)

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
Set OnClickListener、OnLongClickListener、OnTouchListener、OnCheckedChangeListener、ImageLoader and TextWatcher

```kotlin
class HomeFragment : Fragment() {

    private val adapter = RecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // adapter.setOnTouchListener(OnTouchListener listener);
        // adapter.setOnLongClickListener(OnLongClickListener listener);
        // adapter.setOnCheckedChangeListener(OnCheckedChangeListener listener);
        adapter.onClickListener = View.OnClickListener { v ->

            // AdapterUtils
            val holder: AdapterViewHolder = AdapterUtils.getHolder(v)// get ViewHolder
            val textView = holder.itemView.findViewById<TextView>(R.id.item_poster_title)// find view
            val item: MovieItem = holder.item()// get Item
            val itemCount: Int = holder.itemCount// get adapter data size
            val position = holder.bindingAdapterPosition // get item position
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

data set changed callback , it's useful to show or hide empty view
```kotlin
adapter.setOnDataSetChanged(OnDataSetChanged onDataSetChanged);
```

# License  
[The MIT License ](https://opensource.org/licenses/MIT)
