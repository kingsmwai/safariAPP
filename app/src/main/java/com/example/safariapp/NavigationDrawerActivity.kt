package com.example.safariapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class NavigationDrawerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    //intialize the variable to tag the DrawerLayout Class
    private lateinit var drawer : DrawerLayout
    private lateinit var toolbar : Toolbar  //toolbar
    private lateinit var navView : NavigationView  //toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_drawer)

        //view identification
        drawer = findViewById(R.id.drawer_layout)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        navView = findViewById(R.id.nav_drawer)
        //giving nav view a listener
        navView.setNavigationItemSelectedListener(this)

        //new instance of the ActionBarToggle class so that we can get the hamburger icon
        val toggle: ActionBarDrawerToggle = ActionBarDrawerToggle(this,drawer, toolbar,
            R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        //attach a drawer listener for the toggle effect
        drawer.addDrawerListener(toggle)
        //sync state
        toggle.syncState()


        //check if fragment is null
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,FragmentA()).commit() // this line attaches a fragment to the view
            navView.setCheckedItem(R.id.frag_a)
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            //what happens when relative is clicked
            R.id.nav_relative -> {
                val intentRelative = Intent(this@NavigationDrawerActivity,RelativeLayout::class.java)
                startActivity(intentRelative)
            }
            //what happens when linear is clicked
            R.id.nav_linear -> {
                val intentLinear = Intent(this@NavigationDrawerActivity,LinearLayout::class.java)
                startActivity(intentLinear)
            }
            //what happens when constraint is clicked
            R.id.nav_constraint -> {
                val intentConstraint = Intent(this@NavigationDrawerActivity,ConstraintLayout::class.java)
                startActivity(intentConstraint)
            }
            //what happens when random is clicked
            R.id.nav_random -> {
                   Toast.makeText(this,"clicked , nav links work",Toast.LENGTH_LONG).show()
            }
            //bottom nav
            R.id.nav_bottom -> {
                val intentBottom = Intent(this@NavigationDrawerActivity,BottomNavigation::class.java)
                startActivity(intentBottom)
            }

            //fragment switching
            R.id.frag_a -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,FragmentA()).commit()
            }
            R.id.frag_b -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,FragmentB()).commit()
            }

            //lifecycle activity
            R.id.nav_lifecycle -> {
                val intentLife = Intent(this@NavigationDrawerActivity,LifeCycleActivity::class.java)
                startActivity(intentLife)
            }

            //intent sharing
            R.id.nav_intent_sharing -> {
                val intentSharingNav = Intent(this@NavigationDrawerActivity, IntentSharingA::class.java)
                startActivity(intentSharingNav)
            }

            //fragment sharian via a ViewModel
            R.id.nav_fragment_viewmodel -> {
                val intentSharingNav = Intent(this@NavigationDrawerActivity, FragmentSharingViewModel::class.java)
                startActivity(intentSharingNav)
            }

            //fragment sharing via interface
            R.id.nav_fragment_sharing -> {
                val intentSharingNav = Intent(this@NavigationDrawerActivity, FragmentSharing::class.java)
                startActivity(intentSharingNav)
            }

            R.id.nav_listview -> {
                val intentLists = Intent(this@NavigationDrawerActivity, ListViewActivity::class.java)
                startActivity(intentLists)
            }
            R.id.nav_recycler -> {
                val intentLists = Intent(this@NavigationDrawerActivity, RecyclerViewActivity::class.java)
                startActivity(intentLists)
            }
            R.id.nav_con2 -> {
                val intentCon2 = Intent(this@NavigationDrawerActivity, ConstraintLayout2::class.java)
                startActivity(intentCon2)
            }
            R.id.nav_background -> {
                val intentBack = Intent(this@NavigationDrawerActivity, BackgroundLogic::class.java)
                startActivity(intentBack)
            }
            R.id.nav_sqlite -> {
                val intentBack = Intent(this@NavigationDrawerActivity, SQLiteDataActivity::class.java)
                startActivity(intentBack)
            }
            R.id.nav_shareds -> {
                val intentBack = Intent(this@NavigationDrawerActivity, SharedPreferencesActivity::class.java)
                startActivity(intentBack)
            }
        }
        return true
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
           drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}