package com.example.rattan

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.rattan.databinding.ActivityMainBinding
import com.example.rattan.fragmant_main.CartFragment
import com.example.rattan.fragmant_main.ChatFragment
import com.example.rattan.fragmant_main.PersonFragment
import com.example.rattan.fragment.DashboardFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setCurrentFragment(DashboardFragment())

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId){
                R.id.nav_home -> {setCurrentFragment(DashboardFragment())}
                R.id.nav_chat -> {setCurrentFragment(ChatFragment())}
                R.id.nav_cart -> {setCurrentFragment(CartFragment())}
                R.id.nav_person -> {setCurrentFragment(PersonFragment())}
            }
            true
        }





    }
    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }


}