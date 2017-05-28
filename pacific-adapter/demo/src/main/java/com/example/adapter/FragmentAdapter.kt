package com.example.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class FragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return RecyclerViewFragment.newInstance()
            1 -> return ListViewFragment.newInstance()
            2 -> return GridViewFragment.newInstance()
            3 -> return ViewPagerFragment.newInstance()
            else -> return AboutFragment.newInstance()
        }
    }

    override fun getCount(): Int = 5

    override fun getPageTitle(position: Int): CharSequence {
        when (position) {
            0 -> return "RecyclerView"
            1 -> return "ListView"
            2 -> return "GridView"
            3 -> return "ViewPager"
            else -> return "About"
        }
    }
}
