package com.somya.foodapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.somya.foodapp.fragment.OrderHistoryFragment
import com.somya.foodapp.fragment.ProfileFragment
import com.somya.foodapp.R
import com.somya.foodapp.fragment.FaqFragment
import com.somya.foodapp.fragment.FavouritesFragment
import com.somya.foodapp.fragment.Home

class MainDisplayScreenDishes : AppCompatActivity() {
    lateinit var drawerLayout:DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView:NavigationView
    lateinit var toolbar:Toolbar
    var previousMenuItem:MenuItem?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_display_screen_dishes)
        drawerLayout =findViewById(R.id.drawerLayout)
        coordinatorLayout =findViewById(
            R.id.coordinatorLayout
        )
        frameLayout =findViewById(R.id.frameLayout)
        navigationView =findViewById(
            R.id.navigationView
        )
        toolbar =findViewById(R.id.ToolbarMainDisplayScreenDishes)
        setUpToolbar()
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, Home())
            .commit()
        supportActionBar?.title="Home"
        navigationView.setCheckedItem(
            R.id.home
        )
        val actionBarDrawerToggle=ActionBarDrawerToggle(
            this@MainDisplayScreenDishes,
            drawerLayout,
            R.string.open_drawer,
            R.string.open_drawer
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        //adding click listeners to the navigation drawer items
        navigationView.setNavigationItemSelectedListener {
            if(previousMenuItem != null){
                previousMenuItem?.isChecked=false
            }
            it.isCheckable=true
            it.isChecked=true
            previousMenuItem =it
            when(it.itemId) {
                R.id.home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frameLayout,
                            Home()
                        )
                        .commit()
                    supportActionBar?.title="Home"
                    drawerLayout.closeDrawers()

                }
                R.id.profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frameLayout,
                            ProfileFragment()
                        )
                        .commit()
                    supportActionBar?.title="Profile"
                    drawerLayout.closeDrawers()
                }
                R.id.favourite -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frameLayout,
                            FavouritesFragment()
                        )
                        .commit()
                    supportActionBar?.title="Favourites"
                    drawerLayout.closeDrawers()
                }
                R.id.orderHistory -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frameLayout,
                            OrderHistoryFragment()
                        )
                        .commit()
                    supportActionBar?.title="Order History"
                    drawerLayout.closeDrawers()
                }
                R.id.faq -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frameLayout,
                            FaqFragment()
                        )
                        .commit()
                    supportActionBar?.title="FAQs"
                    drawerLayout.closeDrawers()
                }
                R.id.logout -> {
                }
            }
            return@setNavigationItemSelectedListener true
        }
    }
    fun setUpToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title="Mommy Kitchen"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id=item.itemId
        if(id==android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }
return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val frag=supportFragmentManager.findFragmentById(R.id.frameLayout)
        when(frag){
            !is Home -> { supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, Home())
                .commit()
                supportActionBar?.title="Home"}
            else-> super.onBackPressed()
        }
    }
}