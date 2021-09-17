package com.cuty.nicky.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Entity (tableName = "tabla_carrito")
@Parcelize
data class CarritoItem(
        @ColumnInfo(name= "nombreCarritoItem")
        val nombreCarritoItem: String,
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")
        var id: Int = 0,
        @ColumnInfo(name = "precio")
        val precio: Int,
        @ColumnInfo(name = "adherezos")
        var adherezos: String,
        @ColumnInfo(name = "imagen")
        val imagen: String
) : Parcelable
