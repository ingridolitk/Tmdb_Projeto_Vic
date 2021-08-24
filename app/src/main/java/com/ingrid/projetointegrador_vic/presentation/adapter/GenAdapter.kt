package com.ingrid.projetointegrador_vic.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.ingrid.projetointegrador_vic.R
import com.ingrid.projetointegrador_vic.domain.model.Genre
import kotlinx.android.synthetic.main.rv_item_genres.view.*

class GenAdapter(
    private val list: List<Genre>,
    private val clickListener: (Genre) -> Unit,
) : RecyclerView.Adapter<GenAdapter.GenViewHolder>() {

    private val selectedItems: MutableList<Int> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenAdapter.GenViewHolder {
        val chip = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.rv_item_genres,
                parent,
                false)
        return GenViewHolder(chip)
    }

    override fun onBindViewHolder(holder: GenViewHolder, position: Int) {

        val currentItem = list[position]

        if (holder is GenViewHolder)
            holder.itemGen.text = currentItem.name
        holder.itemGen.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedItems.add(currentItem.id)
            } else {
                selectedItems.remove(currentItem.id)
            }

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class GenViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemGen: Chip = itemView.itemGen
    }

}