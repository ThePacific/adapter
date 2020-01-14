package com.example.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FragmentAdapter(
    fm: FragmentManager
) : FragmentPagerAdapter(fm, BEHAVIOR_SET_USER_VISIBLE_HINT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> RecyclerViewFragment.newInstance()
            1 -> ListViewFragment.newInstance()
            2 -> GridViewFragment.newInstance()
            3 -> ViewPagerFragment.newInstance()
            else -> AboutFragment.newInstance()
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
