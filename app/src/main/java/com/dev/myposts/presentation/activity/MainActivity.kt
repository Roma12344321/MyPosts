package com.dev.myposts.presentation.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dev.myposts.R
import com.dev.myposts.presentation.fragments.HomeFragment
import com.dev.myposts.presentation.fragments.PersonFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_container, HomeFragment.newInstance()).commit()
                    true
                }
                R.id.person -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_container, PersonFragment.newInstance()).commit()
                    true
                }
                else -> false
            }
        }
    }
}