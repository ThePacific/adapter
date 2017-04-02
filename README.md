## Adapter

+A quick adapter library for RecyclerView, GridView, ListView, ViewPager, Spinner. It abstracts the boilerplate of item view types, item layouts, viewholders, span sizes , and more, in order to simplify building complex screens with multiple view types. [1.x](https://github.com/thepacific/adapter/blob/master/README-old.md) is deprecated and please upgrade to 2.x

### Features
+ Support DataBinding.
+ Multiple view types with No ViewHolder
+ Simple, flexible 

### Setup
```groovy
compile "com.android.support:recyclerview-v7:{lastest version}"
```

### Items
```java
It will come soon
```

#### Multiple view types
```groovy
It will come soon
```

#### DataBinding
```java
It will come soon
```

#### Attach Listeners
For now , it supports to attach OnClickListener, OnLongClickListener, OnTouchListener, OnCheckedChangeListener to items. The Adapter level API:
Here's the demo:
```java
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
        //So don't use GetAdapterPosition() to get adapter position when you are using
        //ListView, GridView, ViewPager, Spinner, or you will always get 0

        int position = holder.getCurrentPosition();//works for all adapter views
        int position = holder.getAdapterPosition();//works for RecyclerView
    }
});
```
And more:
```java
/**
 * add OnClickListener
 *
 * @param layout   item layout resource id
 * @param listener
 */
void addOnClickListener(@LayoutRes int layout, OnClickListener listener);

/**
 * add OnTouchListener
 *
 * @param layout   item layout resource id
 * @param listener
 */
void addOnTouchListener(@LayoutRes int layout, OnTouchListener listener);

/**
 * add OnLongClickListener
 *
 * @param layout   item layout resource id
 * @param listener
 */
void addOnLongClickListener(@LayoutRes int layout, OnLongClickListener listener);

/**
 * add CompoundButton.OnCheckedChangeListener
 *
 * @param layout   item layout resource id
 * @param listener
 */
void addOnCheckedChangeListener(@LayoutRes int layout, OnCheckedChangeListener listener);
```
Why we need layoutId parameter ? Because we may have the same viewId in different view types.

### Expand
```java
It will come soon
```

### Dependencies
```groovy
compile "com.android.support:recyclerview-v7:$rootProject.ext.support"
provided "com.android.databinding:adapters:$rootProject.ext.dataBinding"
provided "com.android.databinding:library:$rootProject.ext.dataBinding"
provided "com.android.databinding:baseLibrary:$rootProject.ext.dataBindingBaseLibrary"
```

### License  
[The MIT License ](https://opensource.org/licenses/MIT)
