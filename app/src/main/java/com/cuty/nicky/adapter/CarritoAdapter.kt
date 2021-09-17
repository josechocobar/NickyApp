package com.cuty.nicky.adapter

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.cuty.nicky.R
import com.cuty.nicky.base.BaseViewHolder
import com.cuty.nicky.models.CarritoItem

class CarritoAdapter(
        private val context: Context,
        private val carritoList: List<CarritoItem>,
        private val itemClickListener: OnCarritoItemClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {
    interface OnCarritoItemClickListener {
        fun onCarritoClick(item: CarritoItem, position: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return CarritoViewHolder(
                LayoutInflater.from(context).inflate(R.layout.itemview_carrito, parent,false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is CarritoViewHolder -> carritoList.get(position).let { holder.bind(it,position) }
        }
    }

    override fun getItemCount(): Int {
        return carritoList.size
    }



    inner class CarritoViewHolder(itemView:View) : BaseViewHolder<CarritoItem>(itemView){
        private val carritoTitle : TextView = itemView.findViewById(R.id.tvTitulo)
        private val carritoIngredientes : TextView = itemView.findViewById(R.id.tvIngredientes)
        private val carritpPrecio : TextView = itemView.findViewById(R.id.tv_precio_carrito)
        private val carritoButton : ImageButton = itemView.findViewById(R.id.buBorrarCarrito)
        private val imageCarrito:ImageView = itemView.findViewById<ImageView>(R.id.imagenComida)

        override fun bind(item: CarritoItem,position: Int){
            carritoTitle.text = (item.nombreCarritoItem)
            carritoIngredientes.text = item.adherezos
            carritpPrecio.text = item.precio.toString()
            Glide.with(context).load(item.imagen).transform(RoundedCorners(200)).centerCrop().into(itemView.findViewById(R.id.imagenComida))
            itemView.findViewById<ImageButton>(R.id.buBorrarCarrito).setOnClickListener {
                itemClickListener.onCarritoClick(item, position)
            }
        }
    }


}
