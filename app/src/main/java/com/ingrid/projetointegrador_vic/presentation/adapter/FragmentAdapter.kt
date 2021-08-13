package com.ingrid.projetointegrador_vic.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ingrid.projetointegrador_vic.presentation.allmovie.FragmentAllMovie
import com.ingrid.projetointegrador_vic.presentation.favorite.FragmentFavorite

class FragmentAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa)  {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> FragmentAllMovie()
            1 -> FragmentFavorite()
            else -> FragmentAllMovie()
        }
    }
}