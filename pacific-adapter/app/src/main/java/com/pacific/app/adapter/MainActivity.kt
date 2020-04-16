package com.pacific.app.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.pacific.app.adapter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mediator: TabLayoutMediator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = Adapter(this)
        mediator = TabLayoutMediator(binding.layoutTab, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Home"
                else -> "About"
            }
        }
        mediator.attach()
    }


    class Adapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> HomeFragment.newInstance()
                else -> AboutFragment.newInstance()
            }
        }
    }
}
