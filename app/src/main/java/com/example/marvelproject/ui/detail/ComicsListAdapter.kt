package com.example.marvelproject.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelproject.R
import com.example.marvelproject.data.entity.CharacterResults

import com.example.marvelproject.data.entity.comics.ComicsResults
import com.example.marvelproject.ui.list.CharacterListAdapter
import kotlinx.android.synthetic.main.item_character.view.*
import kotlinx.android.synthetic.main.item_comics.view.*

class ComicsListAdapter : RecyclerView.Adapter<ComicsListAdapter.ComicsViewHolder>() {
    inner class ComicsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<ComicsResults>() {
        override fun areItemsTheSame(oldItem: ComicsResults, newItem: ComicsResults): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ComicsResults, newItem: ComicsResults): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
        return ComicsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_comics,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        val comics = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this)
                .load("${comics?.thumbnail?.path}.${comics?.thumbnail?.extension}")
                .into(imgComic)
            textComicName.text = comics.title
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}