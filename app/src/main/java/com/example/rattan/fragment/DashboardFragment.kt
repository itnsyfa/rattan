package com.example.rattan.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.rattan.DashboardPagerAdapter
import com.example.rattan.R
import com.example.rattan.databinding.FragmentDashboardBinding
import com.google.android.material.tabs.TabLayoutMediator


class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private val tabTitles = arrayListOf("Terbaru", "Termurah", "Terlaris")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(layoutInflater)
        setUpTabLayoutPagerWithViewPager()
        return binding.root




    }

    private fun setUpTabLayoutPagerWithViewPager(){
        binding.viewPager.adapter = DashboardPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout,binding.viewPager) {tab, positiion ->
            tab.text = tabTitles[positiion]
        }.attach()

        for(i in 0..3){
            val textView = LayoutInflater.from(requireContext()).inflate(R.layout.tab_title, null)
            as TextView
            binding.tabLayout.getTabAt(i)?.customView = textView
        }
    }
}