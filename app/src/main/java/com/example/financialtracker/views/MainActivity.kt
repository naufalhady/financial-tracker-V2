package com.example.financialtracker.views

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.financialtracker.views.fragment.AddTransactionFragment
import com.example.financialtracker.R
import com.example.financialtracker.views.fragment.HomeFragment
import com.example.financialtracker.views.fragment.PemasukanFragment
import com.example.financialtracker.views.fragment.PengeluaranFragment
import com.example.financialtracker.views.fragment.ProfilFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        loadFragment(HomeFragment())
        bottomNav = findViewById(R.id.bottomNav) as BottomNavigationView
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    loadFragment(HomeFragment())
                    true
                }

                R.id.nav_pemasukan -> {
                    loadFragment(PemasukanFragment())
                    true
                }
                R.id.nav_add -> {
                    loadFragment(AddTransactionFragment())
                    true
                }
                R.id.nav_pengeluaran -> {
                    loadFragment(PengeluaranFragment())
                    true
                }

                R.id.nav_profil -> {
                    loadFragment(ProfilFragment())
                    true
                }
                else -> false
            }
        }
    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

    }


}