package com.jesusrosas.kairosds.bankairos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.jesusrosas.kairosds.bankairos.databinding.ItemCardBinding
import com.jesusrosas.kairosds.bankairos.ui.account.CardItem

class CardAdapter : RecyclerView.Adapter<CardViewHolder>() {
    private lateinit var mContext: Context
    private var isLoading = false
    private var cards: List<CardItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder =
        parent.viewHolderFrom(ItemCardBinding::inflate, ::CardViewHolder)

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item: CardItem = cards[position]
        holder.bind(item)
    }

    fun set(
        isLoading: Boolean,
        cardList: List<CardItem>?
    ) {
        this.isLoading = isLoading
        this.cards = cardList ?: emptyList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = if (isLoading) 0
    else cards.size

    private fun <VDB : ViewDataBinding, T : RecyclerView.ViewHolder> ViewGroup.viewHolderFrom(
        constructor: (LayoutInflater, ViewGroup, Boolean) -> VDB,
        viewHolderConstructor: (VDB) -> T
    ) = viewHolderConstructor(constructor(LayoutInflater.from(context), this, false))
}