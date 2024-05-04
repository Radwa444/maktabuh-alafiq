package com.example.maktabuhalafiq.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.maktabuhalafiq.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class AuthMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_main)
    }
}