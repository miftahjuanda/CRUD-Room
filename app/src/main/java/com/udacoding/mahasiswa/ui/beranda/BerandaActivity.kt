package com.udacoding.mahasiswa.ui.beranda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.udacoding.mahasiswa.R
import kotlinx.android.synthetic.main.activity_beranda.*

class BerandaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)

        val navController = Navigation.findNavController(this, R.id.nav_host_Fragment_home)

        NavigationUI.setupWithNavController(bottom_nav, navController)
    }
}