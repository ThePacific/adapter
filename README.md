# Adapter
A quick adapter library for RecyclerView, GridView, ListView, ViewPager, Spinner. It abstracts the boilerplate of item view types, item layouts, viewholders, span sizes , and more, in order to simplify building complex screens with multiple view types.

[![Download](https://img.shields.io/maven-central/v/com.github.thepacific/adapter.svg)](https://search.maven.org/artifact/com.github.thepacific/adapter)[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Adapter-green.svg?style=true)](https://android-arsenal.com/details/1/3449)

![](https://github.com/thepacific/adapter/blob/master/previews/preview0.png)

# Features
+ Support DataBinding.
+ Multiple view types without any ViewHolder
+ Simple, flexible

# Setup
```groovy
compile 'com.github.thepacific:adapter:2.2.0'
```
# Proguard
```groovy
-keep public class androidx.databinding.DataBindingUtil {
    public static androidx.databinding.ViewDataBinding bind(android.view.View);
}
-keep public class android.databinding.DataBindingUtil {
    public static android.databinding.ViewDataBinding bind(android.view.View);
}
```

# Item
Extend [SimpleRecylcerItem](https://github.com/thepacific/adapter/blob/master/pacific-adapter/adapter/src/main/java/com/pacific/adapter/SimpleRecyclerItem.java). Optionally, you may implement [RecyclerItem](https://github.com/thepacific/adapter/blob/master/pacific-adapter/adapter/src/main/java/com/pacific/adapter/RecyclerItem.java)

```java
public class YourItem extends SimpleRecyclerItem {
    String name;
    String description;
    String imageUrl;

    @Override
    public int getLayout() {
        return R.layout.item_cartoon;
    }

    @Override
    public void bind(ViewHolder holder) {
        // bind data
        DefaultBinding binding = holder.binding();
        binding.setText(R.id.text_name, name);
        binding.setText(R.id.text_desc, description);

        // attach listeners or load image
        holder.attachImageLoader(R.id.img_header);
        holder.attachOnClickListener(R.id.layout_root);

        // holder.attachOnLongClickListener(viewId);
        // holder.attachOnTouchListener(viewId);
        // holder.attachOnCheckedChangeListener(viewId);
    }

    // You may override any other method
}
```

# DataBinding
```java
public class YourItem extends SimpleRecyclerItem {
    @Override
    public void bind(ViewHolder holder) {
        // Without DataBinding, just use DefaultBinding
        DefaultBinding binding = holder.binding();

        // With DataBinding, use layout generated Binding instead of DefaultBinding
        LayoutGeneratedBinding binding = holder.binding();
    }
}
```

# Listeners
Set OnClickListener, OnLongClickListener, OnTouchListener, OnCheckedChangeListener and ImageLoader:

```java
RecyclerAdapter adapter = new RecyclerAdapter(); // RecyclerView
AbsAdapter adapter = new AbsAdapter(int viewTypeCount);// GridView, ListView, Spinner
PagerAdapter2 adapter2 =new PagerAdapter2();// ViewPager

// set listeners or ImageLoader
adapter.setOnClickListener(OnClickListener listener);
adapter.setOnTouchListener(OnTouchListener listener);
adapter.setOnLongClickListener(OnLongClickListener listener);
adapter.setOnCheckedChangeListener(OnCheckedChangeListener listener);
adapter.setImageLoader(ImageLoader imageLoader);

adapter.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        // get ViewHolder
        ViewHolder holder = AdapterUtils.getHolder(v);

        // find view in ItemView
        TextView textName = AdapterUtils.findView(holder.itemView, R.id.text_name);

        // get Item
        Item item = holder.getItem();

        // get adapter data size
        int size = holder.getSize();

        holder.isFirstItem();
        holder.isLastItem();

        // for ListView,GridView, ViewPager, Spinner and RecyclerView
        int position = holder.getCurrentPosition();
         
        // only for RecyclerView
        int position = holder.getAdapterPosition();
    }
});

adapter.setImageLoader((imageView, holder) -> {
    YourItem item = holder.getItem();
    Glide.with(this)
                .load(item.imageUrl())
                .fitCenter()
                .into(imageView);
});
```

# Others
```java
adapter.setOnDataSetChanged(OnDataSetChanged onDataSetChanged);

//  data set changed callback , it's useful to show or hide empty view
public interface OnDataSetChanged {
    /**
     * called when data source changed
     *
     * @param count current data source size
     */
    void apply(int count);
}
```

# License  
[The MIT License ](https://opensource.org/licenses/MIT)
