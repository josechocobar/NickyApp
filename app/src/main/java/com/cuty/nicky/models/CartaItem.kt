 package com.cuty.nicky.models

 import android.os.Parcelable
 import androidx.room.Entity
 import com.google.gson.annotations.SerializedName
 import kotlinx.parcelize.Parcelize


 @Parcelize
data class CartaItem (
        @SerializedName("nombreItemCarta")
        val nombreItemCarta : String,
        @SerializedName("id")
        val id : Int,
        @SerializedName("precio")
        val precio : Int,
        @SerializedName("ingredientes")
        val ingredientes : String,
        @SerializedName("imagen")
        val imagen : String
        ) : Parcelable

 @Entity
 data class CartaItemList(
                      val itemCartaList: List<CartaItem>)