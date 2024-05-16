package com.example.maktabuhalafiq.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.maktabuhalafiq.ArchivesFragment
import com.example.maktabuhalafiq.AuthorFragment
import com.example.maktabuhalafiq.CardFragment
import com.example.maktabuhalafiq.ui.home.HomeFragment
import com.example.maktabuhalafiq.R
import com.example.maktabuhalafiq.databinding.ActivityUserMainBinding
import com.example.maktabuhalafiq.ui.category.CategoryFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class UserMainActivity  : AppCompatActivity() {
    lateinit var binding:ActivityUserMainBinding
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

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


}