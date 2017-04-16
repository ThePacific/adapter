## Adapter

A quick adapter library for RecyclerView, GridView, ListView, ViewPager, Spinner. It abstracts the boilerplate of item view types, item layouts, viewholders, span sizes , and more, in order to simplify building complex screens with multiple view types. [1.x](https://github.com/thepacific/adapter/blob/master/README-old.md) is deprecated and please upgrade to 2.x[ ![2.x Download](https://api.bintray.com/packages/thepacific/maven/adapter/images/download.svg) ](https://bintray.com/thepacific/maven/adapter/_latestVersion)

### Features
+ Support DataBinding.
+ Multiple view types with No ViewHolder
+ Simple, flexible 

### Setup
```groovy
compile 'com.github.thepacific:adapter:2.0.1'
```

### Items
Extend  SimpleRecylcerItem or RecylcerItem in RecyclerView

And extend SimpleItem or Item in GridView, ListView, ViewPager, Spinner

//it looks like below:
```java
public class YourItem extends SimpleItem { // or extend SimpleRecylcerItem

    @Override
    public int getLayout() {
        return R.layout.item_cartoon;
    }

    @Override
    public void bind(ViewHolder holder) {
        // bind data
    }

    //You may override any other method
}
```

#### Multiple view types
GridView, ListView, Spinner:
```java

//Just point out how many view type you have is ok
//You don't need to do any other thing
AbsAdapter adapter = new AbsAdapter(int viewTypeCount);

```
ViewPager:
```java

//Just like below
PagerAdapter2 adapter2 =new PagerAdapter2();

```

RecyclerView:
```java

//Just like below
RecyclerAdapter adapter = new RecyclerAdapter();

```

#### DataBinding
```java
public class YourItem extends SimpleItem {// or extend SimpleRecylcerItem
    @Override
    public void bind(ViewHolder holder) {
        // if you don't use DataBinding , just use the DefaultBinding instead of layout generated Binding
        DefaultBinding binding = holder.binding(); 

        // if you use DataBinding, just use the layout generated Binding
        LayoutGeneratedBinding = holder.binding();
        
    }
}
```

#### Add listeners and image loader
Add OnClickListener, OnLongClickListener, OnTouchListener, OnCheckedChangeListener and ImageLoader:

```java
void addOnClickListener(@LayoutRes int layout, OnClickListener listener);

void addOnTouchListener(@LayoutRes int layout, OnTouchListener listener);

void addOnLongClickListener(@LayoutRes int layout, OnLongClickListener listener);

void addOnCheckedChangeListener(@LayoutRes int layout, OnCheckedChangeListener listener);

void addImageLoader(ImageLoader imageLoader);

//it looks like below:
adapter.addOnClickListener(R.layout.item, new View.OnClickListener() {
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

        //Note: !!!
        //GetCurrentPosition() works for ListView,GridView, ViewPager, Spinner
        //and RecyclerView, but GetAdapterPosition() only works for RecyclerView. Why?
        //Because ViewHolder extends RecyclerView.ViewHolder and RecyclerView.ViewHolder
        //has nothing to do with ListView, GridView, ViewPager and Spinner.
        //So don't use GetAdapterPosition() when you are using ListView, GridView,
        //ViewPager, Spinner, or you will always get -0

        int position = holder.getCurrentPosition();//for all adapter views
        int position = holder.getAdapterPosition();//only for RecyclerView
    }
});

```
Why we need layoutId parameter ? Because we may have the same viewId in different view types.

#### Attach listeners and image loader
Attach listeners and image loader from ViewHolder. Just do it in Item.bind(holder) method

```java
    void attachOnClickListener(@IdRes int viewId);

    void attachOnTouchListener(@IdRes int viewId);

    void attachOnLongClickListener(@IdRes int viewId);

    void attachOnCheckedChangeListener(@IdRes int viewId);

    void attachImageLoader(@IdRes int viewId);
```

//it looks like below:

public class YourItem extends SimpleItem {// or extend SimpleRecylcerItem

    @Override
    public void bind(ViewHolder holder) {
        DefaultBinding binding = holder.binding();
        binding.setText(R.id.text_name, name());
        binding.setText(R.id.text_desc, description());
        holder.attachImageLoader(R.id.img_header);
        holder.attachOnClickListener(R.id.layout_root);
        // and so on ......
    }
}

```

### Expand

Extend Item, RecyclerItem and Base***Adapter by yourself, just like the SimpleItem, SimpleRecylerItem and ***Adapter do.

### Dependencies
```groovy
compile "com.android.support:recyclerview-v7:$rootProject.ext.support"
provided "com.android.databinding:adapters:$rootProject.ext.dataBinding"
provided "com.android.databinding:library:$rootProject.ext.dataBinding"
provided "com.android.databinding:baseLibrary:$rootProject.ext.dataBindingBaseLibrary"
```

### License  
[The MIT License ](https://opensource.org/licenses/MIT)
