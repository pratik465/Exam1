package com.example.exam1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.exam1.databinding.ActivityMainBinding
import com.example.exam1.fragments.Add_Fragment
import com.example.exam1.fragments.Category_Fragment
import com.example.exam1.fragments.Home_Fragment
import com.example.exam1.fragments.NotificationFragment

import com.example.exam1.fragments.Profile_Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(Home_Fragment())

        binding.bottomNavigation.setOnNavigationItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {

                when (item.itemId) {
                    R.id.home -> {
                        loadFragment(Home_Fragment())
                    }


                    R.id.add -> {
                        loadFragment(Add_Fragment())
                    }

                }

                return true
            }

        })

    }

    private fun loadFragment(fragment: Fragment) {

        supportFragmentManager.beginTransaction().replace(R.id.fragFrame,fragment).commit()

    }
}