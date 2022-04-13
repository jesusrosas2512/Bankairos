package com.jesusrosas.kairosds.bankairos.ui.account

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jesusrosas.kairosds.bankairos.databinding.ItemCardBinding
import com.jesusrosas.kairosds.bankairos.ui.account.CardItem

class CardViewHolder(private val mBinding: ItemCardBinding) : RecyclerView.ViewHolder(mBinding.root) {



    fun bind(card: CardItem){

        mBinding.tvCard.text = card.name
        mBinding.tvType.text = card.type
    }
}