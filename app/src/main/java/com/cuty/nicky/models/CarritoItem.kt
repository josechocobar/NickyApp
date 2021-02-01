package com.cuty.nicky.models

data class CarritoItem(
        val nombreCarritoItem : String,
        val id : Int,
        val precio : Int,
        val adherezos : List<String>,
        val imagen : String
)