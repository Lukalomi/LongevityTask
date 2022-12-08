package com.example.longevitytask.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.longevitytask.R
import com.example.longevitytask.databinding.SingleItemBinding
import com.example.longevitytask.domain.model.HomeAnimeModel

class AnimeHomeAdapter(
    private val context: Context,
    var onClickListener: ((HomeAnimeModel.HomeAnimeModelItem) -> Unit)? = null

) : RecyclerView.Adapter<AnimeHomeAdapter.ItemViewHolder>() {

    var list: List<HomeAnimeModel.HomeAnimeModelItem> = mutableListOf()
    fun submitList(newList: List<HomeAnimeModel.HomeAnimeModelItem>) {
        list = newList
    }

    inner class ItemViewHolder(val binding: SingleItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SingleItemBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        Glide.with(context)
            .load(list[position].animeImg)
            .error(R.drawable.ic_launcher_background)
            .into(holder.binding.ivItem)
        holder.binding.apply {
            tvItem.text = list[position].animeTitle
            SingleCardLayout.setOnClickListener{
                onClickListener?.invoke(list[position])

            }

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}