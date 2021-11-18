package com.example.marvelproject.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelproject.R
import com.example.marvelproject.data.entity.CharacterResults
import kotlinx.android.synthetic.main.item_character.view.*


class CharacterListAdapter : RecyclerView.Adapter<CharacterListAdapter.CharacterViewHolder>() {
    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<CharacterResults>() {
        override fun areItemsTheSame(
            oldItem: CharacterResults,
            newItem: CharacterResults
        ): Boolean {
            return oldItem.characterId == newItem.characterId
        }

        override fun areContentsTheSame(
            oldItem: CharacterResults,
            newItem: CharacterResults
        ): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_character,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this)
                .load("${character?.thumbnail?.path}.${character?.thumbnail?.extension}")
                .into(imageView)
            characterName.text = character.name
            setOnClickListener {
                onItemClickListener?.let { it(character) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((CharacterResults) -> Unit)? = null

    fun setOnItemClickListener(listener: (CharacterResults) -> Unit) {
        onItemClickListener = listener
    }
}