package com.example.rattan

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.rattan.fragment.TerbaruFragment
import com.example.rattan.fragment.TerlarisFragment
import com.example.rattan.fragment.TermurahFragment

class DashboardPagerAdapter(fragment:Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> TerbaruFragment()
            1 -> TermurahFragment()
            else -> TerlarisFragment()
        }
    }
}