package com.ingrid.projetointegrador_vic.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ingrid.projetointegrador_vic.R
import com.ingrid.projetointegrador_vic.R.layout
import com.ingrid.projetointegrador_vic.presentation.adapter.FragmentAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        val viewpager = findViewById<ViewPager2>(R.id.viewPager)
        val tablayout = findViewById<TabLayout>(R.id.tab_layoutopc)

        viewpager.adapter = FragmentAdapter(this)
        viewpager.isUserInputEnabled=false

        TabLayoutMediator(tablayout, viewpager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()
        viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
            }
        })
    }
    private fun getTabTitle(position: Int): String {
        return when (position) {
            0 -> "Todos os filmes"
            1 -> "Favoritos"
            else -> ""
        }
    }
}
