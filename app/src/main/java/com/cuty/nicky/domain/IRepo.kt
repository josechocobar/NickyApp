package com.cuty.nicky.domain

import androidx.lifecycle.LiveData
import com.cuty.nicky.models.CarritoItem
import com.cuty.nicky.models.CartaItem
import com.cuty.nicky.vo.Resource

interface IRepo {
    suspend fun getItemCartaList() : Resource<List<CartaItem>>

}