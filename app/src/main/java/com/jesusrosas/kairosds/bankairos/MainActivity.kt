package com.jesusrosas.kairosds.bankairos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp

class MainActivity : AppCompatActivity(){

/*    private val appBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(R.id.menuFragment, R.id.addCardFragment),
            findViewById<DrawerLayout>(R.id.drawer_layout)
        )
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

/*    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override val navController: NavController
        get() = (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).navController*/
}