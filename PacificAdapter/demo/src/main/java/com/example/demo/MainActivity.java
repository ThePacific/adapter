package com.example.demo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import com.example.demo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        adapter = new FragmentAdapter(getSupportFragmentManager());
        binding.viewPager.clearOnPageChangeListeners();
        binding.layoutTab.clearOnTabSelectedListeners();
        binding.layoutTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        binding.viewPager.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(binding.layoutTab));
        binding.layoutTab.setupWithViewPager(binding.viewPager);
        binding.viewPager.setAdapter(adapter);
    }
}
