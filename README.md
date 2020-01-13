# Adapter
A quick adapter library for RecyclerView, GridView, ListView, ViewPager, Spinner. It abstracts the boilerplate of item view types, item layouts, viewholders, span sizes , and more, in order to simplify building complex screens with multiple view types. [1.x](https://github.com/thepacific/adapter/blob/master/README-old.md) is deprecated and please upgrade to 2.x

[ ![Download](https://api.bintray.com/packages/thepacific/maven/adapter/images/download.svg) ](https://bintray.com/thepacific/maven/adapter/_latestVersion)[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Adapter-green.svg?style=true)](https://android-arsenal.com/details/1/3449)

![](https://github.com/thepacific/adapter/blob/master/art/exam.gif)

# Features
+ Support DataBinding.
+ Multiple view types without any ViewHolder
+ Simple, flexible

# Setup
```groovy
compile 'com.github.thepacific:adapter:{lastestVersion}'
```
# Proguard
```groovy
-keep public class android.databinding.DataBindingUtil {
    public static android.databinding.ViewDataBinding bind(android.view.View);
}
```

# Item
Extend [SimpleItem](https://github.com/thepacific/adapter/blob/master/PacificAdapter/adapter/src/main/java/com/pacific/adapter/SimpleItem.java) or [SimpleRecylcerItem](https://github.com/thepacific/adapter/blob/master/PacificAdapter/adapter/src/main/java/com/pacific/adapter/SimpleRecyclerItem.java). Optionally, you may implement [Item](https://github.com/thepacific/adapter/blob/master/PacificAdapter/adapter/src/main/java/com/pacific/adapter/Item.java) or [RecylcerItem](https://github.com/thepacific/adapter/blob/master/PacificAdapter/adapter/src/main/java/com/pacific/adapter/RecyclerItem.java)

```java
public class YourItem extends SimpleItem {
    String name;
    String description;
    String imageUrl;

    @Override
    public int getLayout() {
        return R.layout.item_cartoon;
    }

    @Override
    public void bind(ViewHolder holder) {
        //bind data
        DefaultBinding binding = holder.binding();
        binding.setText(R.id.text_name, name);
        binding.setText(R.id.text_desc, description);

        //attach listeners or load image
        holder.attachImageLoader(R.id.img_header);
        holder.attachOnClickListener(R.id.layout_root);

        //holder.attachOnLongClickListener(viewId);
        //holder.attachOnTouchListener(viewId);
        //holder.attachOnCheckedChangeListener(viewId);
    }

    //You may override any other method
}
```

# DataBinding
```java
public class YourItem extends SimpleItem {
    @Override
    public void bind(ViewHolder holder) {
        //Without DataBinding, just use DefaultBinding
        DefaultBinding binding = holder.binding();

        //With DataBinding, use layout generated Binding instead of DefaultBinding
        LayoutGeneratedBinding binding = holder.binding();
    }
}
```

# Listeners
Set OnClickListener, OnLongClickListener, OnTouchListener, OnCheckedChangeListener and ImageLoader:

```java
RecyclerAdapter adapter = new RecyclerAdapter(); //RecyclerView
AbsAdapter adapter = new AbsAdapter(int viewTypeCount);//GridView, ListView, Spinner
PagerAdapter2 adapter2 =new PagerAdapter2();//ViewPager

//set listeners or ImageLoader
adapter.setOnClickListener(OnClickListener listener);
adapter.setOnTouchListener(OnTouchListener listener);
adapter.setOnLongClickListener(OnLongClickListener listener);
adapter.setOnCheckedChangeListener(OnCheckedChangeListener listener);
adapter.setImageLoader(ImageLoader imageLoader);

adapter.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        //get ViewHolder
        ViewHolder holder = AdapterUtil.getHolder(v);

        //find view in ItemView
        TextView textName = AdapterUtil.findView(holder.itemView, R.id.text_name);

        //get Item
        Item item = holder.getItem();

        //get adapter data size
        int size = holder.getSize();

        holder.isFirstItem();
        holder.isLastItem();

        //for ListView,GridView, ViewPager, Spinner and RecyclerView
        int position = holder.getCurrentPosition();
         
        //only for RecyclerView
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

//data set changed callback , it's useful to show or hide empty view
public interface OnDataSetChanged {
    //called when data size is 0
    void onEmptyData();

    //called when data size larger than 0
    void onHasData();
}
```

# License  
[The MIT License ](https://opensource.org/licenses/MIT)
