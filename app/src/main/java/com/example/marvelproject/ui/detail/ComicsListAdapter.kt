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
import com.example.marvelproject.data.entity.Comics
import com.example.marvelproject.data.entity.Item
import com.example.marvelproject.ui.list.CharacterListAdapter
import kotlinx.android.synthetic.main.item_character.view.*

class ComicsListAdapter: RecyclerView.Adapter<ComicsListAdapter.ComicsViewHolder>() {
    inner class ComicsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback=object : DiffUtil.ItemCallback<Item>(){
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.name==newItem.name
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem==newItem
        }
    }
    val differ= AsyncListDiffer(this,differCallback)

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
        val comics=differ.currentList[position]
        holder.itemView.apply {
            /*Glide.with(this)
                .load("${comics?.thumbnail?.path}.${comics?.thumbnail?.extension}")
                .into(imageView)*/
            characterName.text=comics.name
            setOnClickListener {
                onItemClickListener?.let { it(comics) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener:((Item)->Unit)?=null

    fun setOnItemClickListener(listener:(Item)->Unit){
        onItemClickListener=listener
    }
}