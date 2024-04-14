package com.example.maktabuhalafiq.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.maktabuhalafiq.ArchivesFragment
import com.example.maktabuhalafiq.AuthorFragment
import com.example.maktabuhalafiq.CardFragment

import com.example.maktabuhalafiq.CategoryFragment
import com.example.maktabuhalafiq.HomeFragment
import com.example.maktabuhalafiq.R
import com.example.maktabuhalafiq.databinding.ActivityUserMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class UserMainActivity : AppCompatActivity() {
    lateinit var binding:ActivityUserMainBinding
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityUserMainBinding.inflate(layoutInflater)

            binding.buttomMenu.setOnItemSelectedListener{menu ->when(menu.itemId){
            R.id.homeFragment-> {
                replaceragment(HomeFragment())
                true
            }
            R.id.categoryFragment-> {
                replaceragment(CategoryFragment())
                true
            }
            R.id.authorFragment -> {
                replaceragment(AuthorFragment())
                true
            }
            R.id.archivesFragment -> {
                replaceragment(ArchivesFragment())
                true
            }
            R.id.cardFragment -> {
                replaceragment(CardFragment())
                true
            }
             else -> false
        }
        }
        replaceragment(HomeFragment())
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun replaceragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace( R.id.fragmentContainerView2, fragment).commit()
    }

    private fun nav_menu() {
        val bottomNavigationMenuView = findViewById<BottomNavigationView>(R.id.buttomMenu)
        val navController = findNavController(R.id.fragmentContainerView2)

        val appBar = AppBarConfiguration(
            setOf(
                R.id.archivesFragment,
                R.id.authorFragment,
                R.id.categoryFragment,
                R.id.homeFragment,



            )
        )
        setupActionBarWithNavController(navController, appBar)
        bottomNavigationMenuView.setupWithNavController(navController)
    }
}