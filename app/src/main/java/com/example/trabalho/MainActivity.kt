package com.example.trabalho

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.trabalho.fragments.ListaFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        // Fragment inicial: Receitas a Aprender
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListaFragment.newInstance(false))
                .commit()
        }

        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_a_aprender -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, ListaFragment.newInstance(false))
                        .commit()
                    true
                }
                R.id.menu_aprendidas -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, ListaFragment.newInstance(true))
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}