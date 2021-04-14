package com.example.safariapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigation : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    //intialize variable to tag bottom nav
    private lateinit var bottomNav: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        //view identification
        bottomNav = findViewById(R.id.bottomView)
        //set a listener to it
        bottomNav.setOnNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_linear -> {
                val intentLinear= Intent(this@BottomNavigation,LinearLayout::class.java)
                startActivity(intentLinear)
            }
            R.id.nav_relative -> {
                val intentRelative = Intent(this@BottomNavigation,RelativeLayout::class.java)
                startActivity(intentRelative)
            }
            R.id.nav_constraint -> {
                val intentConstraint = Intent(this@BottomNavigation,ConstraintLayout::class.java)
                startActivity(intentConstraint)
            }
        }
        return true
    }
}