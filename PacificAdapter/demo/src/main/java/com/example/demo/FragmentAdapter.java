package com.example.demo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {
    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return RecyclerViewFragment.newInstance();
            case 1:
                return ListViewFragment.newInstance();
            case 2:
                return GridViewFragment.newInstance();
            case 3:
                return ViewPagerFragment.newInstance();
            default:
                return AboutFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "RecyclerView";
            case 1:
                return "ListView";
            case 2:
                return "GridView";
            case 3:
                return "ViewPager";
            default:
                return "About";
        }
    }
}
