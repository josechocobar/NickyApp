package com.cuty.nicky.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.cuty.nicky.R
import com.cuty.nicky.base.BaseViewHolder
import com.cuty.nicky.models.CartaItem

class MainAdapter(
        private val context: Context, private val tragosList: List<CartaItem>,
        private val itemClickListener: OnTragoClickListener
) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {
    interface OnTragoClickListener {
        fun onTragoClick(drink: CartaItem,position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_rv_carta, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return tragosList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(tragosList[position], position)
        }
    }

    inner class MainViewHolder(itemView: View) : BaseViewHolder<CartaItem>(itemView) {

            override fun bind(item: CartaItem, position: Int) {

            Glide.with(context).load(item.imagen).transform(RoundedCorners(20)).into(itemView.findViewById(R.id.img_item))
            itemView.findViewById<TextView>(R.id.tv_title_item).text = item.nombreItemCarta
            itemView.findViewById<TextView>(R.id.tv_ingredientes_item).text = item.ingredientes
            itemView.findViewById<TextView>(R.id.tv_precio).text = item.precio.toString()

            itemView.findViewById<ImageButton>(R.id.buAdherezos).setOnClickListener {
                itemClickListener.onTragoClick(item,position)}
        }
    }
}