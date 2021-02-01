package com.cuty.nicky.domain

import com.cuty.nicky.models.CartaItem
import com.cuty.nicky.vo.Resource

interface IRepo {
    suspend fun getItemCartaList() : Resource<List<CartaItem>>
}