package com.example.noteapp.presentation.activitys

import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.noteappaiditek.R
import com.example.noteappaiditek.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Установите Toolbar как ActionBar
        setSupportActionBar(binding.toolbar)

        // Связываем NavController с NavHostFragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        // Настраиваем ActionBar
        NavigationUI.setupWithNavController(binding.navigationView, navController)

//        val appBarConfiguration = AppBarConfiguration(
//            setOf(R.id.mainFragment), // Главные фрагменты (где гамбургер-меню вместо "Назад")
//            binding.drawerLayout
//        )
        setupActionBarWithNavController(navController)

        binding.toolbar.setNavigationOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        navController.addOnDestinationChangedListener{_,destination,_->
            when (destination.id){
                R.id.mainFragment ->{
                    binding.toolbar.visibility = View.VISIBLE
                }
                R.id.detailsFragment ->{
                    binding.toolbar.visibility = View.GONE
                }
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}