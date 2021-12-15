package com.example.episodatenow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.episodatenow.databinding.ActivityMainBinding
import com.example.episodatenow.viewmodel.EpisoDateViewModel


class MainActivity : AppCompatActivity() {


    lateinit var navController : NavController
    lateinit var binding : ActivityMainBinding
    lateinit var viewModel : EpisoDateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(EpisoDateViewModel::class.java)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)

        /*if(item.itemId == R.id.tvShowFromFragment){
            viewModel.select(TvShow.empty())
        }

        return NavigationUI.onNavDestinationSelected(item!!,
            navController || super.onOptionsItemSelected(item))*/
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}