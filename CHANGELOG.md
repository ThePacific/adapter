Change Log
==========

Version 3.0.0 *(2020-04-26)*
----------------------------

**This release ships with kotlin**

* Kotlin version 1.3.72

**New Features**
 * Remove FragmentAdapter2 and FragmentStateAdapter2 support because of ViewPager2.
 * Remove ListView and GridView support. Let's move to RecyclerView together.
 * Remove Item and SimpleItem, use RecyclerItem and SimpleRecyclerItem instead.
 * Change 'RecyclerItem<T : AdapterViewHolder>' to RecyclerItem. No more generic type.
 * Rename ViewHolder to AdapterViewHolder、ImageLoader to AdapterImageLoader
 * Add useful APIS in RecyclerAdapter、AdapterViewHolder、RecyclerItem、AdapterUtils. 
   More detail, please check the source files.
 

Version 2.2.0 *(2020-03-28)*
----------------------------

* Upgrade to AndroidX
* Remove class Item and SimpleItem，use RecyclerItem instead
* Add TextWatcher for TextView and EditText
